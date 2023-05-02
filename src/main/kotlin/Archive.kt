import java.util.*

class Archive(var name: String) {

    var notesList: MutableList<Note> = mutableListOf()

    companion object {
        fun create(): Archive {
            println("Введите название архива")
            val input = Scanner(System.`in`)
            val name = (input.nextLine())
            val newArchive = Archive(name)
            println("Архив $name создан")
            return newArchive
        }
    }
}

class Note(var name: String, var textNote: String) {

    companion object {
        fun create(): Note {
            println("Введите имя заметки")
            val input = Scanner(System.`in`)
            val name = (input.nextLine())
            println("Введите текст заметки")
            val textNote = (input.nextLine())
            val newNote = Note(name, textNote)
            println("Заметка $name создана")
            return newNote
        }
    }

}