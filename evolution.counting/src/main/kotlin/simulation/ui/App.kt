package simulation.ui

import simulation.CreatureTracker
import simulation.Simulation
import java.awt.*
import java.util.*
import javax.swing.*

class App: JFrame() {

  val sim = Simulation()

  val simDisplay = DefaultListModel<CreatureTracker>()

  val genToColor = mutableMapOf<String, Color>()

  init {
    title = "Simple example"
    setSize(1000, 1000)
    setLocationRelativeTo(null)
    defaultCloseOperation = EXIT_ON_CLOSE

    add(JPanel(BorderLayout()).apply {
      add(JList<CreatureTracker>().apply {
        cellRenderer = CreatureListCellRenderer()
        model = simDisplay
      })
    }, BorderLayout.PAGE_END)

    add(JPanel(BorderLayout()).apply {
      add(object: JPanel() {
        override fun paintComponent(g: Graphics?) {
          super.paintComponent(g)
          if (sim.creatureSnapshots.size == 0) return
          val g2 = g as Graphics2D
          val genWidth = width / sim.creatureSnapshots.size
          var currX = 0
          sim.creatureSnapshots.forEach { generation ->
            var currY = 0
            generation.species.toSortedMap().forEach {
              val percentage = it.value.size / generation.creatures.size.toDouble()
              val calculatedHeight = percentage*height
              if (!genToColor.containsKey(it.key)) genToColor[it.key] = randomColor()
              g2.color = genToColor[it.key]
              g2.fillRect(currX, currY, genWidth, calculatedHeight.toInt())
              currY += calculatedHeight.toInt()
            }
            currX += genWidth
          }
        }
      })
      add(JPanel().apply {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        sim.addGenListener {
          removeAll()
          sim.creatureSnapshots.last().species.toList().sortedByDescending { it.second.size }.take(8).forEach {
            add(JLabel().apply {
              foreground = genToColor[it.first]
              text = "${it.first}: ${it.second.size}"
            })
          }
        }
      }, BorderLayout.EAST)
    })

    add(JPanel().apply {
      add(JButton().apply {
        text = "run"
        addActionListener {
          sim.runGeneration()
          simDisplay.clear()
          sim.creatureSnapshots.last().creatures.sortedByDescending { it.result() }.take(20).forEach {
            simDisplay.addElement(it)
          }
          this@App.repaint()
        }
      })
      add(JButton().apply {
        text = "run 100"
        addActionListener {
          (1..100).forEach { sim.runGeneration() }
          simDisplay.clear()
          sim.creatureSnapshots.last().creatures.sortedByDescending { it.result() }.take(20).forEach {
            simDisplay.addElement(it)
          }
          this@App.repaint()
        }
      })
    }, BorderLayout.PAGE_START)
  }
}

class CreatureListCellRenderer: ListCellRenderer<CreatureTracker> {
  override fun getListCellRendererComponent(list: JList<out CreatureTracker>?, value: CreatureTracker?, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {
    return JPanel().apply {
      add(JLabel().apply {
        text = "${value?.result()}"
      })
      add(JLabel().apply {
        text = "${value?.creature?.species()}"
      })
      value?.creature?.features?.forEach {
        add(JLabel().apply {
          text = it.toString()
        })
      }

    }
  }
}

val r = Random()
fun randomColor(): Color {
  return Color(r.nextInt(256), r.nextInt(256), r.nextInt(256))
}