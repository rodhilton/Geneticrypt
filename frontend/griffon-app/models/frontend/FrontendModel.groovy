package frontend

class FrontendModel {
    @Bindable String cipherText
    @Bindable String originalCipherText
    @Bindable String key
    @Bindable boolean running = false
    @Bindable String availableAction = "Crack!"
}