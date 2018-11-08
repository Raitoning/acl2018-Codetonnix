package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;
import model.cases.CaseTresor;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class Jeu implements Game {

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */

	private Heros heros;
	private Labyrinthe labyrinthe;

	public Jeu(String source, Heros heros) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
		this.heros = heros;
		this.labyrinthe = new Labyrinthe();
	}

    public Heros getHeros() {
        return heros;
    }

    /**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande direction de l'evoluion
	 */
	@Override
	public void evolve(Cmd commande) {
		//System.out.println("Execute "+commande);
		if (commande!=Cmd.IDLE) {
            heros.deplacer(commande, labyrinthe);
        }else if ((labyrinthe.getCases()[heros.getPosX() / 20 ][heros.getPosY() / 20].hasAction())){
			labyrinthe.getCases()[heros.getPosX() / 20 ][heros.getPosY() / 20].action();
		}

	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		return !heros.isAlive();
	}

	public Labyrinthe getLabyrinthe() {
		return labyrinthe;
	}
}