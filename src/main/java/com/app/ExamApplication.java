package com.app;

import com.app.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
    Lista przebojow

    Mamy utwor muzyczny, ktory opisywany jest przez pola title, author, genre (POP, ROCK, ...).
    Mamy usera, ktory bedzie glosowal na utwor. Kazdy user posiada username, password, email, age oraz role.
    Uzytkownicy ktorzy maja role USER moga glosowac. Uzytkownicy, ktorzy maja role ADMIN moga dodawac utwory.
    Nalezy rowniez w glosowaniu uwzglednic date glosowania.
    Przygotuj funkcjonalnosc, ktora zawiera:

    1. Rejestracje usera
    2. Dodawanie nowego utowru - rola ADMIN
    3. Glosowanie na utwor - rola USER
    4. Statystyki:
    a. zwracanie x najpopularniejszych utworow
    b. zwracanie x najpopularniejszych utworow, na ktore glosowal uzytkownik o nazwie username
    c. zwracanie zestawienie w ktorym podajesz gatunek oraz najpopularniejszy w tym gatunku utwor
    d. zwracanie x najpopularniejszych utworow w miesiacu i roku, w ktore podasz jako parametr

    Zastosuj:

    1. JDK 11+
    2. Maven
    3. Dockeryzajca do obrazu
    4. Rest Spring Security
    5. MySQL
    6. Mechanizm obslugi wyjatkow
 */

@SpringBootApplication
@RequiredArgsConstructor
public class ExamApplication {

    private final VoteRepository voteRepository;

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }


}
