package example.imageviewer.ble

/** Информация об обнаруженном устройстве */
actual class BleDevice {
    actual val id: String
        get() = TODO("Not yet implemented")
    actual val name: String?
        get() = TODO("Not yet implemented")
}

/** Менеджер для работы с Bluetooth LE */
actual class BleManager actual constructor() {
    /** Начать сканирование. Каждый найденный девайс передаётся в [onDeviceFound]. */
    actual fun startScan(onDeviceFound: (BleDevice) -> Unit) {}

    /** Остановить сканирование. */
    actual fun stopScan() {}

    /**
     * Отправить [data] в характеристику [characteristicUuid]
     * устройства [device] (или по его id).
     */
    actual fun sendBytes(
        device: BleDevice,
        serviceUuid: String,
        characteristicUuid: String,
        data: ByteArray,
    ) {}

}