package me.pisal.abaclone.di

import io.bloco.faker.Faker
import me.pisal.hybridcrypto.aes.AESCipher
import me.pisal.hybridcrypto.hybrid.HybridCrypto
import me.pisal.hybridcrypto.rsa.RSACipher
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(qualifier = named("SERVER_RSA_PUBLIC_KEY")) {
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1FUK9Kl0oZMYTUYlrCfC\n" +
                "rWInovEu4Gk4W5HUUW1aytU8zO6rGNNUO/riLVdEEaVudHHwSI6Pln3m3GkvYH2w\n" +
                "a32WcNCI9kJ3jO0RJu+xLHwoHpWNqYQrzg98PdA2G2RikirNC86r0hBXv2pfCiiW\n" +
                "68FL7oSKrUS2c5XHabPsKJlqBVfw270c3tUTd5MZeOcRlmr6K+QJC4GQ+yD4yqGN\n" +
                "w31YGCZmW3sewgcSxmSqX/+t7GDaeIH75u3LiNbpAgWtuKaTkrhmCzUuK79nvpp4\n" +
                "wc8sUL+a8QBw0Xm3PmS3WQ1RVMo/ngpHiG/9xKv7/7ALfnWEZb+O5zwghGOPZWLS\n" +
                "WwIDAQAB"
    }
    single { AESCipher(16) }
    single {
        RSACipher(get(named("SERVER_RSA_PUBLIC_KEY")))
    }
    single {
        HybridCrypto.initialize(
            configuration = HybridCrypto.Configuration.default,
            publicKey = get(named("SERVER_RSA_PUBLIC_KEY"))
        )
        HybridCrypto.getInstance()
    }
    single { Faker() }
}
