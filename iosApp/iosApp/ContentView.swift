import UIKit
import SwiftUI
import shared

struct ContentView: View {
    private let bleManager = BleManager()
    var body: some View {
        ZStack {
            ComposeView()
                    .ignoresSafeArea(.all) // Compose has own keyboard handler
            VStack {
                gradient.ignoresSafeArea(edges: .top).frame(height: 0)
                Spacer()
                
                Button(action: {
                    // запуск сканирования BLE
                    print("tup ble connect")
                    bleManager.startScan { BleDevice in
                        print("МЫ НАШЛИ УСТРОЙСТВО \(BleDevice.name)!!!")
                    }
                }) {
                    Text("ble connect")
                        .font(.headline)
                        .padding()
                        .frame(maxWidth: .infinity)
                        .background(Color.blue)
                        .foregroundColor(.white)
                        .cornerRadius(10)
                }
                .padding(.horizontal)
                .padding(.bottom, 48)
            }
        }.preferredColorScheme(.dark)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        let controller = Main_iosKt.MainViewController()
        controller.overrideUserInterfaceStyle = .light
        return controller
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

let gradient = LinearGradient(
    colors: [
        Color.black.opacity(0.6),
        Color.black.opacity(0.6),
        Color.black.opacity(0.5),
        Color.black.opacity(0.3),
        Color.black.opacity(0.0),
    ],
    startPoint: .top, endPoint: .bottom
)
