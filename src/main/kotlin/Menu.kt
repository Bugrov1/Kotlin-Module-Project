import java.util.*

class Command(private val name: String, val action: (Int) -> Unit) {
    override fun toString(): String {
        return name
    }
}

abstract class BaseMenu {

    private var running = true

    protected val scanner = Scanner(System.`in`)
    protected open val commands = mutableListOf<Command>()

    abstract fun show()

    protected fun run(commands: List<Command>) {
        while (running) {
            commands.forEachIndexed { index, command ->
                println("$index. $command")
            }
            val input = scanner.nextLine()
            if (checkInput(input, commands.size - 1)) {
                val commandIndex = input.toInt()
                commands[commandIndex].action.invoke(commandIndex)
            }
        }
    }

    protected fun onBack() {
        running = false
    }

    private fun checkInput(input: String, maxNum: Int): Boolean {

        if (Character.isDigit(input.toCharArray()[0])) {

            if (input.toInt() in 0..maxNum) {
                return true
            } else {
                println("Такого пункта нет в списке , выберите из доступных в списке")
                return false
            }
        } else {
            println("Необходимо ввести число!!!")
            return false
        }
    }
}

class ArchivesMenu : BaseMenu() {
    private val archives = mutableListOf<Archive>(Archive("Arch 1"))

    override var commands = mutableListOf<Command>()

    //Лямбда для открытия архива
    private val openArchive: (Int) -> Unit = { absoluteIndex ->
        val archiveIndex = absoluteIndex - 1 //Отнимаем 1, так как первая команда у нас про создание архива
        val archive = archives[archiveIndex]
        val notesMenu = NotesMenu(archive)
        println("Вы находитесь в архиве \"${archive.name}\" ")
        notesMenu.show()
        //Открываем экран архива

    }

    override fun show() {
        commands = mutableListOf<Command>().apply {
            add(
                Command("Создать архив") { createArchive() }
            )
            archives.forEach {
                add(
                    Command(it.name, openArchive) //like createArchiveCommand(архив)
                )
            }
            add(
                Command("Выход из приложения") { onBack() }
            )
        }
        run(commands)
    }

    private fun createArchive() {
        //Создаем архив и добавляем его к архивам и в список комманд
        val newArchive = Archive.create()
        archives.add(newArchive)
        commands.add(commands.size - 1, Command(newArchive.name, openArchive))
    }
}

class NotesMenu(archive: Archive) : BaseMenu() {
    private var notes = archive.notesList

    override var commands = mutableListOf<Command>()

    //Лямбда для открытия архива
    private val openNote: (Int) -> Unit = { absoluteIndex ->
        val noteIndex = absoluteIndex - 1
        val note = notes[noteIndex]
        println("Вы просматриваете записку \"${note.name}\" в архиве  \"${archive.name}\" ")
        ////Открыть текст записки
        val textMenu = TextMenu(note)
        textMenu.show()

    }

    override fun show() {
        commands = mutableListOf<Command>().apply {
            add(
                Command("Создать записку") { createNote() }//записку создать функция
            )
            notes.forEach {
                add(
                    Command(it.name, openNote)
                )
            }
            add(
                Command("Вернуться в меню \"Архивы\"") { onBack() }
            )
        }
        run(commands)
    }

    private fun createNote() {
        //Создаем архив и добавляем его к архивам и в список комманд
        val newNote = Note.create()
        notes.add(newNote)
        commands.add(commands.size - 1, Command(newNote.name, openNote))
    }
}

class TextMenu(note: Note) : BaseMenu() {
    private var text = note.textNote

    override var commands = mutableListOf<Command>()

    //Лямбда для открытия архива
    private val openNote: (Int) -> Unit = { //Отнимаем 1, так как первая команда у нас про создание архива

    }

    override fun show() {
        commands = mutableListOf<Command>().apply {

            add(
                Command(text, openNote)
            )
            add(
                Command("Вернуться в меню \"Записки\"") { onBack() }
            )
        }
        run(commands)
    }

}

