#pragma once

#include <windows.h>
#include <iostream>
#include <cstdlib>
#include <conio.h>
#include <string>
#include <vector>
#define getchKey() static_cast<Key>(_getch()) 

#define sh 9
#define consoleW() SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),15)
#define consoleB() SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),11)
#define consoleR() SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),14)

class Search {
private:
	enum class Key {
		esc = 27,
		enter = 13,

		up = 72,
		down = 80,

		right = 77,
		left = 75,

		cl ='\n'
	};

	int pointer = 0;

	std::string wpomarce; bool wpomarce_ = 0;
	std::string wpomodelu; bool wpomodelu_ = 0;
	int wporokub, wporokue; bool wporokub_=0, wporokue_=0;
	int wpocenieb, wpoceniee; bool wpocenieb_=0, wpoceniee_=0;
	bool wpostanie; bool wpostanie_=0;
	int wpoprzb, wpoprze; bool wpoprzb_=0,wpoprze_=0;
	bool wposkrz; bool wposkrz_=0;

	std::vector<Samochod> new_s;
	
	void wpoma();
	void wpomo();
	void wpor();
	void wpoc();
	void wpos();
	void wpop();
	void wpot();
	void wyswietl(std::vector<Samochod>);
	void check(std::vector<Samochod>);
	void przegladaj();
public:

	Search() = default;
	Search(std::vector<Samochod>& new_s);
	std::string wyszukiwanie[sh] = {
	"\t1. Wyszukaj po marce",
	"\t2. Wyszukaj po modelu",
	"\t3. Wyszukaj po roku produkcji",
	"\t4. Wyszukaj po cenie",
	"\t5. Wyszukaj po stanie",
	"\t6. Wyszukaj po przebiegu",
	"\t7. Wyszukaj po typie skrzyni biegow",
	"\t8. Wyswietl wynik",
	"\t9. Wyjscie"
	};

	static void wpofunc(std::string tab, std::string, bool *, bool *);
	static void wpofunc(std::string tab,std::string,std::string, int, int, bool, bool);

	void wyszukaj(std::vector<Samochod>*);
	void print();
	~Search() = default;

};