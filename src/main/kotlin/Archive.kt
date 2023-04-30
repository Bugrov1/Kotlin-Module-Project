import java.util.*

class Archive(var name:String,) {

    var notesList:MutableMap<Int,Notes> = mutableMapOf()
    fun addNoteArchive(note:Notes){
        var indexNew = notesList.size+1
        notesList[indexNew] = note
    }
    fun addToStorage(storage:MutableMap<Int,Archive >,newArchive: Archive ) {
        val lastItem = storage.size+1
        storage[lastItem] = newArchive
    }
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
class Notes() {
    var notesMap: MutableMap<String, String> = mutableMapOf()
    companion object {
        fun create():Notes {
            println("Введите имя заметки")
            val input = Scanner(System.`in`)
            val name =(input.nextLine())
            println("Введите текст заметки")
            val textNote =(input.nextLine())
            val newNote = Notes()
            newNote.notesMap=mutableMapOf(name to textNote)
            println("Заметка $name создана")
            return newNote
        }
    }

}