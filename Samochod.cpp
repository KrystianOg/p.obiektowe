#include "Samochod.h"

Samochod::Samochod(unsigned nr, std::string marka, std::string model, unsigned rok, int cena, bool stan, int przebieg, bool typ_skrzyni)
{
	this->nr = nr;
	this->marka = marka;
	this->model = model;
	this->rok = rok;
	this->cena = cena;
	this->stan = stan;
	this->przebieg = przebieg;
	this->typ_skrzyni = typ_skrzyni;
}

Samochod& Samochod::operator=(const Samochod& other)
{
	this->nr = other.nr;
	this->marka = other.marka;
	this->model = other.model;
	this->rok = other.rok;
	this->cena = other.cena;
	this->stan = other.stan;
	this->przebieg = other.przebieg;
	this->typ_skrzyni = other.typ_skrzyni;
	return *this;
}

Samochod::Samochod(const Samochod& other)
{
	this->nr = other.nr;
	this->marka = other.marka;
	this->model = other.model;
	this->rok = other.rok;
	this->cena = other.cena;
	this->stan = other.stan;
	this->przebieg = other.przebieg;
	this->typ_skrzyni = other.typ_skrzyni;
}
