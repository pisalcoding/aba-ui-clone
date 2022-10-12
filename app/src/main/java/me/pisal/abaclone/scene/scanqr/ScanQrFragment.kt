package me.pisal.abaclone.scene.scanqr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.common.base.Objects
import me.pisal.abaclone.thirdparty.mlkit.camera.CameraSource
import me.pisal.abaclone.R
import me.pisal.abaclone.databinding.FragmentScanQrBinding
import me.pisal.abaclone.scene.*
import me.pisal.abaclone.thirdparty.mlkit.barcodedetection.BarcodeField
import me.pisal.abaclone.thirdparty.mlkit.barcodedetection.BarcodeProcessor
import me.pisal.abaclone.thirdparty.mlkit.camera.WorkflowModel
import me.pisal.abaclone.thirdparty.mlkit.camera.WorkflowModel.WorkflowState
import me.pisal.abaclone.thirdparty.mlkit.camera.WorkflowModel.WorkflowState.*
import org.koin.android.ext.android.inject
import java.io.IOException

class ScanQrFragment : BaseFragment(
    sensitive = false,
    requireAuth = false
) {

    private val viewModel by inject<ScanQrViewModel>()
    private val workflowModel by inject<WorkflowModel>()

    private lateinit var binding: FragmentScanQrBinding
    private var cameraSource: CameraSource? = null
    private var currentWorkflowState: WorkflowState? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentScanQrBinding.inflate(inflater, container, false)
        withMainActivity {
            requestPermissionThen(
                permission = Permission.CAMERA,
                onSuccess = {
                    cameraSource = CameraSource(binding.cameraPreviewGraphicOverlay)
                    setUpWorkflowModel()
                }
            )
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setToolbarTitle(getString(R.string.scan_qr))
            safeRunOnUiThread {
                setNavigationBackgroundColor(R.color.black)
            }
        }
        workflowModel.markCameraFrozen()
        currentWorkflowState = WorkflowState.NOT_STARTED
        cameraSource?.setFrameProcessor(BarcodeProcessor(binding.cameraPreviewGraphicOverlay,
            workflowModel))
        workflowModel.setWorkflowState(DETECTING)
    }

    override fun onPause() {
        super.onPause()
        currentWorkflowState = WorkflowState.NOT_STARTED
        stopCameraPreview()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraSource?.release()
        cameraSource = null
    }

    private fun startCameraPreview() {
        val cameraSource = this.cameraSource ?: return
        if (!workflowModel.isCameraLive) {
            try {
                workflowModel.markCameraLive()
                binding.cameraPreview.start(cameraSource)
            } catch (e: IOException) {
                cameraSource.release()
                this.cameraSource = null
            }
        }
    }

    private fun stopCameraPreview() {
        val workflowModel = this.workflowModel
        if (workflowModel.isCameraLive) {
            workflowModel.markCameraFrozen()
            binding.cameraPreview.stop()
        }
    }

    private fun setUpWorkflowModel() {
        // Observes the workflow state changes, if happens, update the overlay view indicators and
        // camera preview state.
        workflowModel.workflowState.observe(viewLifecycleOwner, Observer { workflowState ->
            if (workflowState == null || Objects.equal(currentWorkflowState, workflowState)) {
                return@Observer
            }
            currentWorkflowState = workflowState
            when (workflowState) {
                DETECTING, CONFIRMING -> {
                    startCameraPreview()
                }
                DETECTED, SEARCHING, SEARCHED -> {
                    stopCameraPreview()
                }
                else -> {}
            }
        })

        workflowModel.detectedBarcode.observe(viewLifecycleOwner) { barcode ->
            if (barcode != null) {
                val barcodeFieldList = ArrayList<BarcodeField>()
                barcodeFieldList.add(BarcodeField("Raw Value", barcode.rawValue ?: ""))
            }
        }
    }
}