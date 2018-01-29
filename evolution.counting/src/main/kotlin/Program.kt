import simulation.ui.App
import java.awt.EventQueue


fun main(args: Array<String>) {

  EventQueue.invokeLater {
    val app = App()
    app.isVisible = true
  }
}


