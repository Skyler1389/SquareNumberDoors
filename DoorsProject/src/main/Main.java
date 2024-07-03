package main;

public class Main {
	
	/* Projektvorgaben: 

	Ein Eroberer nahm bei einem Feldzug 100 Gegner in Haft, die er in 100 
	Einzelzellen sperren ließ. An seinem Geburtstag will er durch Großzügigkeit
	glänzen und plant eine Amnestie der in Haft befindlichen Gegner. Allerdings
	will er nicht alle Gegner freilassen und fragt deswegen seinen Hofberater,
	wie er verfahren soll.
	
	Öffne zunächst alle Türen, schließe sodann jede zweite Türe, beim dritten
	Durchgang nehme jede dritte Türe und öffne sie, wenn sie geschlossen ist,
	bzw. schließe sie, wenn sie geöffnet ist. Im vierten Durchgang wiederum
	nehme jede vierte Türe und öffne sie, wenn sie geschlossen ist, bzw.
	umgekehrt. Verfahre nun weiter so bis zum 100. Durchgang.
	
	Wie viele Türen (nach dem 100. Durchgang) stehen offen und welches sind 
	ihre Türnummern? Beachte bitte, dass die Nummerierung der Türen natürlich
	bei 1 beginnt und bei 100 endet. 
	
	Notizen: Nach Implementierung der Algorithmischen Lösung fällt das Muster
	der quadratischen Zahlen auf. Daraus ergibt sich eine effizientere
	mathematische Lösung für die Aufgabe */

	public static String getDoorStateString(boolean state, int iteration) {
		// Baut einen passenden String aus dem Index der Tür und ihrem Zustand.
		String output = "Door " + (iteration + 1) + " is ";
		if (state == true) {
			output += "open";
		}
		else {
			output += "closed";
		}
		return output;
	}
	
	public static void switchDoorStatesEveryN(boolean[] doors, int n) {
		// Wechselt den Zustand jeder n-ten Tür im übergebenen Array.
		for (int iteration = n; iteration < doors.length; iteration += n) {
			doors[iteration-1] = !doors[iteration-1];
		}
	}
	
	public static void mathematicalSolution(boolean[] doors) {
		// Wechselt den Zustand jeder n²-ten Tür, bei der n nach jeder
		// Iteration inkrementiert wird.
		for (int iteration = 0; iteration < doors.length; iteration++) {
			doors[iteration] = false;
		}
		for (int iteration = 1; iteration * iteration < doors.length;
				iteration++) {
			doors[iteration * iteration - 1] = true;
		}
	}
	
	public static void main(String[] args) {
		// 100 Boolean Array zur Darstellung der Türen. Initialzustand false 
		// wird als geschlossen definiert, true als geöffnet
		boolean[] doors = new boolean[100];
		System.out.println("/// ALL DOORS INITIAL STATE ///");
		for (int iteration = 0; iteration < doors.length; iteration++) {
			System.out.println(getDoorStateString(doors[iteration], iteration));
		}
		// Iteriere über das Array und öffne alle Türen
		System.out.println("/// ALL DOORS OPENED ///");
		for (int iteration = 0; iteration < doors.length; iteration++) {
			doors[iteration] = true;
			System.out.println(getDoorStateString(doors[iteration], iteration));
		}
		// Iteriere über alle Türen nach den Vorgaben und wechsel
		// ihren Zustand
		for (int iteration = 2; iteration <= 100; iteration++) {
				switchDoorStatesEveryN(doors, iteration);
		}
		// Gib den Status aller 100 Türen aus
		System.out.println("/// ALL DOORS SWITCHED COMPLETE ///");
		for (int iteration = 0; iteration < doors.length; iteration++) {
			System.out.println(getDoorStateString(doors[iteration], iteration));
		}
		boolean[] doorsAfterAlgorithmicalSolution = doors; // Snapshot des
		// Boolean Arrays für Vergleich mit Mathematischer Methode
		System.out.println("/// ALL DOORS OPENED ///");
		for (int iteration = 0; iteration < doors.length; iteration++) {
			doors[iteration] = true;
			System.out.println(getDoorStateString(doors[iteration], iteration));
		}
		// Öffne alle Türen, dessen Türnummer eine quadratische Zahl ist
		mathematicalSolution(doors);
		// Gib den Status aller 100 Türen aus
		System.out.println("/// ALL DOORS SWITCHED COMPLETE ///");
		for (int iteration = 0; iteration < doors.length; iteration++) {
			System.out.println(getDoorStateString(doors[iteration], iteration));
		}
		boolean[] doorsAfterMathematicalSolution = doors; // Snapshot des
		// Boolean Arrays für Vergleich mit Algorithmischer Methode
		System.out.println("/// COMPARISON ///");
		if (doorsAfterAlgorithmicalSolution
				.equals(doorsAfterMathematicalSolution)) {
			// Gebe eine Nachricht aus, wenn das Boolean Array nach Anwendung 
			// beider Methoden identisch ist.
			System.out.println("Output is equivalent between both solutions.");
		}
	}
}
