#pragma once

#include <string>
#include <vector>

class Samochod {

private:

	

public:
	/*
	Samochod(unsigned nr, std::string marka, std::string model, unsigned rok, int cena, std::string stan, int przebieg, std::string typ_srzyni) {
		this->nr = nr;
		this->marka = marka;
		this->model = model;
		this->rok = rok;
		this->cena = cena;
		this->stan = stan;
		this->przebieg = przebieg;
		this->typ_skrzyni = typ_skrzyni;
	}
	*/

	//Samochod(unsigned id);
	Samochod(unsigned nr,std::string marka, std::string model, unsigned rok, int cena, bool stan, int przebieg, bool typ_skrzyni);

	unsigned nr;
	std::string marka;
	std::string model;
	unsigned rok;
	int cena;
	bool stan;
	int przebieg;
	bool typ_skrzyni;
	Samochod& operator=(const Samochod&);
	Samochod(const Samochod&);
	~Samochod() = default;

};