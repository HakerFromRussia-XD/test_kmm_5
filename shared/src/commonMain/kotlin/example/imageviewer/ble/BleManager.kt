package example.imageviewer.ble

/** Информация об обнаруженном устройстве */
expect class BleDevice {
    val id: String        // идентификатор устройства
    val name: String?     // имя, может быть null
}

/** Менеджер для работы с Bluetooth LE */
expect class BleManager() {
    /** Начать сканирование. Каждый найденный девайс передаётся в [onDeviceFound]. */
    fun startScan(onDeviceFound: (BleDevice) -> Unit)

    /** Остановить сканирование. */
    fun stopScan()

    /**
     * Отправить [data] в характеристику [characteristicUuid]
     * устройства [device] (или по его id).
     */
    fun sendBytes(
        device: BleDevice,
        serviceUuid: String,
        characteristicUuid: String,
        data: ByteArray
    )
}
