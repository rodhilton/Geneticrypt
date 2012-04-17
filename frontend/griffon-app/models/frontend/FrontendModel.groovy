package frontend

import java.util.concurrent.atomic.AtomicInteger

class FrontendModel {
    @Bindable String cipherText
    @Bindable String originalCipherText
    @Bindable String key
    @Bindable boolean running = false
    @Bindable String availableAction = "Crack!"
    @Bindable int generation = 0
}