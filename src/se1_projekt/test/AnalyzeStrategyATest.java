package se1_projekt.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se1_projekt.entities.UserStory;
import se1_projekt.exceptions.ContainerException;
import se1_projekt.model.Container;
import se1_projekt.strategies.AnalyzeStrategyA;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeStrategyATest {

    UserStory us;
    AnalyzeStrategyA ana;

    @BeforeEach
    public void setUp() throws ContainerException {
        us = new UserStory(1, "Test Story", 5, 3, 2,2, 2.00,
                "Student", "Als Student möchte ich mich registrieren, um Zugang zum System zu erhalten");
        Container.getInstance().addUserStory(us);
        ana = new AnalyzeStrategyA();
    }

    @AfterEach
    public void cleanUp(){
        Container.getInstance().deleteUSById(1);
    }

    @Test
    public void aktivTest(){
        assertTrue(ana.aktiv(us.getFunk()));
        us.setFunk("Als Student werde ich registriert, um Zugang zum System zu erhalten");
        assertFalse(ana.aktiv(us.getFunk()));
    }

    @Test
    public void titleTest(){
        assertTrue(ana.title(us.getTitel()));
        us.setTitel("");
        assertFalse(ana.title(us.getTitel()));
    }

    @Test
    public void actTest(){
        assertFalse(ana.act(us.getActor()));
        Container.getInstance().addActor("Student");
        assertTrue(ana.act(us.getActor()));
    }

    @Test
    public void mwTest(){
        assertTrue(ana.mw(us.getFunk()));
        us.setFunk("Als Student will ich mich registrieren können");
        assertFalse(ana.mw(us.getFunk()));
    }

    @Test
    public void complex(){
        assertTrue(ana.complex(us.getFunk()));
        us.setFunk("Student soll sich registrieren und einloggen und abmelden können");
        assertFalse(ana.complex(us.getFunk()));
    }

    @Test
    public void getNoteTest(){
        assertEquals("sehr gut", ana.getNote(90));
        assertEquals("mangelhaft", ana.getNote(30));
    }

    @Test
    public void analyzeUSTest(){
        assertEquals(100, ana.analyzeUS(us.getId()));
        Container.getInstance().deleteAc("Student");
        assertEquals(70, ana.analyzeUS(us.getId())); // Akteur fehlt - 30
        us.setTitel("");
        assertEquals(60, ana.analyzeUS(us.getId())); // Titel fehlt - 10
        us.setFunk("Student Will angemeldet werden, damit ich Zugriff hab");
        assertEquals(40, ana.analyzeUS(us.getId()));// im Passiv formuliert - 20
        us.setFunk("Will angemeldet werden damit und und und");
        assertEquals(30, ana.analyzeUS(us.getId())); // Komplex - 10
        us.setFunk("Will angemeldet werden und und und");
        assertEquals(0, ana.analyzeUS(us.getId())); // Mehrwert -30
        us.setFunk("");
        assertEquals(0, ana.analyzeUS(us.getId())); // leere Funktionalität resultiert auch in -60
    }
}