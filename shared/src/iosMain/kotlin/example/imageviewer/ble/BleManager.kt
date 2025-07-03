package example.imageviewer.ble

import kotlinx.cinterop.ObjCAction
import platform.CoreBluetooth.*
import platform.Foundation.NSData
import platform.Foundation.NSNumber
import platform.darwin.NSObject

actual class BleDevice internal constructor(
    val peripheral: CBPeripheral
) {
    actual val id: String get() = peripheral.identifier.UUIDString()
    actual val name: String? get() = peripheral.name
}

actual class BleManager {
    private var onDeviceCallback: ((BleDevice) -> Unit)? = null
    private val delegate = object : NSObject(),
        CBCentralManagerDelegateProtocol,
        CBPeripheralDelegateProtocol {

        override fun centralManagerDidUpdateState(central: CBCentralManager) {
            // здесь можно отследить включение Bluetooth
        }

        override fun centralManager(
            central: CBCentralManager,
            didDiscoverPeripheral: CBPeripheral,
            advertisementData: Map<Any?, *>,
            RSSI: NSNumber
        ) {
            onDeviceCallback?.invoke(BleDevice(didDiscoverPeripheral))
        }
    }
    private val manager = CBCentralManager(delegate, queue = null)

    actual fun startScan(onDeviceFound: (BleDevice) -> Unit) {
        onDeviceCallback = onDeviceFound
        manager.scanForPeripheralsWithServices(null, null)
    }

    actual fun stopScan() {
        manager.stopScan()
    }

    actual fun sendBytes(
        device: BleDevice,
        serviceUuid: String,
        characteristicUuid: String,
        data: ByteArray
    ) {
        // Подключение и отправка происходит через CoreBluetooth API:
        // - manager.connectPeripheral(...)
        // - peripheral.discoverServices(listOf(CBUUID.UUIDWithString(serviceUuid)))
        // - после нахождения характеристики вызвать writeValue(...)
        // Точные шаги аналогичны любому примеру CoreBluetooth.
    }
}
