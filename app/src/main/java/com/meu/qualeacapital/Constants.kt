package com.meu.qualeacapital

object Constants {

    fun getQuestions():ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val country = listOf("Afeganistão", "Alemanha", "Angola", "Argentina","Argélia","Arábia Saudita","Austrália","Bahamas",
            "Bangladesh", "Bolívia", "Brasil", "Bélgica", "Canadá", "Catar", "Chile", "China", "Colômbia",
            "Coreia do Norte", "Coreia do Sul", "Costa Rica", "Croácia", "Cuba", "Dinamarca", "Egito",
            "Emirados Árabes Unidos", "Equador", "Eslovênia", "Espanha", "Estados Unidos", "Filipinas",
            "França", "Grécia", "Haiti", "Holanda", "Hong Kong", "Irlanda", "Israel", "Itália", "Jamaica",
            "Japão", "Marrocos", "Moçambique", "México", "Nigéria", "Noruega", "Nova Zelândia", "Peru",
            "Paraguai", "Paquistão", "Polônia", "Porto Rico", "Portugal", "Quênia", "Inglaterra",
            "República Tcheca", "Rússia", "Senegal", "Suécia", "Suíça", "Síria", "Tailândia",
            "Tunísia", "Ucrânia", "Uruguai", "Venezuela", "África do Sul", "Áustria", "Índia", "Sudão", "Nepal")
        val capital = listOf("Cabul", "Berlim", "Luanda", "Buenos Aires", "Argel", "Riade", "Canberra","Nassau",
            "Daca" , "Sucre" , "Brasília", "Bruxelas" , "Ottawa" , "Doha" ,"Santiago" , "Pequim" ,
            "Bogotá" , "Pyongyang" , "Seul" ,"São José" , "Zagreb" , "Havana" , "Copenhague" ,
            "Cairo" ,"Abu Dhabi" ,"Quito" ,"Liubliana" ,"Madri" ,"Washington" ,"Manila" ,"Paris" ,
            "Atenas" ,"Porto Príncipe" ,"Amsterdã" ,"Hong Kong" ,"Dublin" ,"Jerusalém" , "Roma" ,
            "Kingston" ,"Tóquio" ,"Rabat" ,"Maputo" ,"Cidade do México" ,"Abuja" ,"Oslo" , "Wellington" ,
            "Lima" ,"Assunção" ,"Islamabade" ,"Varsóvia" ,"São João" , "Lisboa" , "Nairóbi" ,"Londres" ,
            "Praga" , "Moscou" , "Dacar" ,"Estocolmo" , "Berna" ,"Damasco" ,"Bangkok" , "Túnis" ,
            "Kiev" ,"Montevidéu" ,"Caracas" ,"Pretória" ,"Viena" ,"Nova Delhi" ,"Cartum" ,"Katmandu")


        for ( i in 0..country.size-1){
            var a = capital.random()
            var b = capital.random()
            var c = capital.random()

            while (a == capital[i] || a == b || a == c || b == capital[i] || b == c|| c == capital[i] ){
                a = capital.random()
                b = capital.random()
                c = capital.random()
            }

            var listQuestion = mutableListOf<String>(capital[i], a, b, c)
            listQuestion = listQuestion.sorted() as MutableList<String>
            val optionOne = listQuestion[0]
            val optionTwo= listQuestion[1]
            val optionThree= listQuestion[2]
            val optionFour= listQuestion[3]

            val question = Question(
                i,
                country[i],
                optionOne,
                optionTwo,
                optionThree,
                optionFour,
                capital[i]
            )
            questionList.add(question)
        }
        return questionList
    }

    fun searchQuestion(): MutableList<Int> {

        val search = getQuestions().size - 1
        val numbers = 0..search
        var mixNumber = numbers.random()
        val mixList = mutableListOf<Int>()
        for (i in 0..search){
            while (mixList.contains(mixNumber)){
                mixNumber = numbers.random()
            }
            mixList.add(mixNumber)
        }
        return mixList
    }
}