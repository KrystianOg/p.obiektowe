#pragma once
#include <conio.h>
#include <windows.h>
#include <iostream>
#include <vector>
#include <fstream>
#include <string>
#include "Samochod.h"
#include <cstdlib>


//#define getchKey() static_cast<Key>(_getch()) 
//#define consoleW() SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),15)
//#define consoleB() SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),11)
#define cls() system("cls")
#define menudl 7

class Menu {

private:

	enum class Key {
		esc = 27,
		enter = 13,

		up = 72,
		down = 80,

		right=77,
		left=75,
		cl = '\n'
	};

	

	std::fstream plik;
	void waitforkey();
	void print();
	
	
public:
	
	Menu() = default;
	std::vector<Samochod> samochody;
	Menu(std::vector<Samochod>& samochody);
	
	std::string glowne_menu[menudl] = {
	"\t1. Wczytaj dane z pliku",
	"\t2. Zapisz dane do pliku",
	"\t3. Przegladaj samochody",
	"\t4. Szukaj samochodow",
	"\t5. Dodaj samochod",
	"\t6. Usun samochod",
	"\t7. Wyjscie"
	};

	int pointer = 0;

	unsigned int sizeofs();
	void run();
	void wczytaj();
	void zapisz();
	unsigned int przegladaj();
	void dodaj();
	void usun();

	~Menu() = default;
	
};