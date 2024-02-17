class Game {
    var puntuación : Int
    var terminar : Int
    var preguntasRespondidas : Int
    var preguntaAux : Question
    var preguntas : QuestionDataBase
    var aux:String
    var contador:Int
    init {
        puntuación = 0
        terminar = 0
        preguntasRespondidas = 0
        preguntaAux = Question("First", mutableListOf(), 0)
        preguntas = QuestionDataBase("src/kotlin_questions.json")
        aux = "n"
        contador = 0
    }
    fun loopDeJuego ()
    {
        presentación()
        while (terminar == 0)
        {
            preguntar(contador)
            contador++
            verificarSiTerminamos()
        }
    }
    fun presentación ()
    {
        println("Este es un juego de trivia. Consiste en quince rondas.")
        println("Con cada ronda se le hará una pregunta, responda la respuesta correcta y gane puntos.")
        println("Puede terminar el juego despues de cada pregunta.")
        println("El juego terminara de forma automativa luego que las 15 preguntas hayan sido contestadas.")
        println("¿Desea empezar? y/n")
        aux = readln()
        if (aux == "n")
        {
            println("Entonces terminamos por hoy.")
            terminar = 1
        }
        else
        {
            println("Empiezan las preguntas")
        }

    }
    fun preguntar(index:Int)
    {
        preguntaAux = preguntas.devolverPregunta(index)
        println(preguntaAux)
        println("¿Cuál es la respuesta correcta?")
        var respuestaAux : Int = readln().toInt()
        calificarPregunta(preguntaAux, respuestaAux)
    }
    fun verificarSiTerminamos ()
    {
        if (preguntasRespondidas == preguntas.preguntas?.size?.minus(1) )
        {
            println("Todas las preguntas han sido contestadas.")
            mostrarPuntuación()
            terminar = 1

        }
        else
        {
            println("¿Quiere continuar? y/n")
            aux = readln()
            if (aux == "n")
            {
                terminar = 1
                mostrarPuntuación()
                println("¿Desea volver a empezar? y/n")
                aux = readln()
                if (aux == "y")
                {
                    reiniciar()
                }
                else
                {
                    println("Que tenga un buen día.")
                }
            }
        }
    }
    fun reiniciar ()
    {
        puntuación = 0
        terminar = 0
        preguntasRespondidas = 0
        contador = 0
    }
    fun mostrarPuntuación ()
    {
        println("Pregunas respondidas: ${preguntasRespondidas}")
        println("Puntos ${puntuación}")

    }
    fun calificarPregunta(pregunta:Question, respuesta:Int) : Int{
        preguntasRespondidas++
        if (respuesta-1 == pregunta.correctAnswerIndex)
        {
            puntuación++
            println("Respuesta correcta.")
            return 1
        }
        else
        {
            println("Respuesta incorrecta.")
            println("La respuesta correcta era: ${pregunta.correctAnswerIndex+1}")
            return 0
        }
    }
}