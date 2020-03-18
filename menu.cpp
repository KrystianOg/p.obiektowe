#include "menu.h"
#include "Search.h"

Menu::Menu(std::vector<Samochod>& samochody)
{
	this->samochody = samochody;
}

void Menu::print() {

	cls();
	consoleR();
	std::cout << "\t\tMENU GLOWNE\n\n";

	for (int i = 0; i < menudl; i++) {
		if (pointer == i)
		{
			consoleB();
			std::cout << glowne_menu[i] << std::endl;
		}
		else
		{
			consoleW();
			std::cout << glowne_menu[i] << std::endl;
		}

	}
}

void Menu::wczytaj() {
	std::string nazwa_pliku;
	std::cout << "Podaj nazwe pliku: ";
	std::cin >> nazwa_pliku;
	
	samochody.clear();

	plik.open(nazwa_pliku, std::ios::in);

	if (!plik.good()) {
		std::cout << "Nie wczytano pliku.";
		return;
	}

	unsigned nr;
	std::string marka;
	std::string model;
	unsigned rok;
	int cena;
	bool stan;
	int przebieg;
	bool typ_skrzyni;

	//plik >> nr >> marka >> model >> rok >> cena >> stan >> przebieg >> typ_skrzyni;

	while (plik >> nr >> marka >> model >> rok >> cena >> stan >> przebieg >> typ_skrzyni)
		samochody.push_back({ nr,marka,model,rok,cena,stan,przebieg,typ_skrzyni });
	
	plik.close();

	std::cout << "Wczytano plik " << nazwa_pliku;// << ".txt";
}

void Menu::zapisz() {
	std::string nazwa_pliku{ "" };
	std::cout << "Podaj nazwe pliku: ";
	std::cin >> nazwa_pliku;

	plik.open(nazwa_pliku, std::ios::out);

	for (auto x : samochody) //while vector is true - ma elementy 
		plik << x.nr << ' ' << x.marka << ' ' << x.model << ' ' << x.rok << ' ' << x.cena << ' ' << x.stan << ' ' << x.przebieg << ' ' << x.typ_skrzyni << '\n';
	
	plik.close();
		std::cout << "Zapisano dane.";

}

unsigned int Menu::przegladaj() {

	int strona = 0;
	int iloscstr = sizeofs() % 15 + 1;

	int pozycja = 1;
	int size = sizeofs();

	Key keycode;

	while (true) {
		
		cls();
		for (int i = strona * 15; i < (strona + 1) * 15 && i < size ; i++) {

			if (i == pozycja-1)
				consoleB();
			else
				consoleW();

			std::cout << samochody.at(i).nr << ' ' << samochody.at(i).marka << ' ' << samochody.at(i).model << ' ' << samochody.at(i).rok << ' ' << samochody.at(i).cena << ' ';
			if (samochody.at(i).stan == 1)
				std::cout << "Nowy" << ' ';
			else
				std::cout << "Uzywany" << ' ';

			std::cout<<samochody.at(i).przebieg << ' ';
				
				if (samochody.at(i).typ_skrzyni == 1)
					std::cout << "Automatyczna" << ' ';
				else
					std::cout << "Manualna" << ' ';

			std::cout << '\n';
		}

		//if (getchKey() != Key::cl)
		//	continue;

		keycode = getchKey();

		

		if (keycode == Key::right && size > (strona+1) * 15) {
			pozycja = (strona+1) * 15 + 1;
			strona++;
		}


		else if (keycode == Key::left && strona > 0) {
			pozycja = (strona-1)*15+1;
			strona--;
			
		}

		else if (keycode == Key::up){
			if (pozycja == strona * 15+1 && pozycja != 1) {
				strona--;
				pozycja--;
			}

			else if(pozycja==1)
			{ 
			}

			else 
				pozycja--;
			
		}

		else if (keycode == Key::down) {
			if (pozycja == (strona+1) * 15 && pozycja != size ) {
				strona++;

				pozycja++;
			}

			else if (pozycja == size)
			{
			}

			else
				pozycja++;

		}

		else if (keycode == Key::enter) {
			return pozycja;
		}

		else if (keycode == Key::esc)
			return 0;
	}

}


unsigned int Menu::sizeofs() {
	return samochody.size();
}

void Menu::dodaj() {

	std::string marka;
	std::string model;
	unsigned rok;
	int cena;
	bool stan = 0;
	int przebieg;
	bool typ_skrzyni = 0;
	bool nothing = 0;

	std::cout << "\tPodaj marke pojazdu: "; std::cin >> marka; cls();
	std::cout << "\tPodaj model pojazdu: "; std::cin >> model; cls();
	std::cout << "\tPodaj rok produkcji pojazdu: "; std::cin >> rok; cls();
	std::cout << "\tPodaj cene pojazdu: "; std::cin >> cena; cls();

	Search wys2;

	

	std::cout << "\t\tWybierz stan pojazdu : \n\n";

	wys2.wpofunc("Nowy", "Uzywany", &stan, &nothing); cls();
	std::cout << "Podaj przebieg pojazdu: "; std::cin >> przebieg; cls();
	std::cout << "\t\tWybierz typ skrzyni biegow pojazdu: \n\n";

	wys2.wpofunc("Automatyczna", "Manualna", &typ_skrzyni,&nothing); cls();

	samochody.push_back({ sizeofs()+1, marka, model, rok, cena, stan ,przebieg, typ_skrzyni });
	
	std::cout << "\nPomyslnie dodano.";
}

void Menu::usun() {

	unsigned int id;

	
	id = przegladaj();

	if ((id - 1) < samochody.size()) {
		samochody.at(id - 1) = std::move(samochody.back());
		samochody.at(id - 1).nr = id;
		samochody.pop_back();
	}

	std::cout<<"Usunieto.";

	return;
}

void Menu::waitforkey() {
	Key keycode;

	do{
		keycode = getchKey();

	} while (keycode != Key::esc && keycode != Key::enter);

}

void Menu::run() {

	Key keycode;

	while (true) {

		print();
		keycode = getchKey();

		if (keycode == Key::up) {
			pointer -= 1;

			if (pointer == -1)
				pointer = menudl-1;
		}

		else if (keycode == Key::down) {
			pointer += 1;

			if (pointer == menudl)
				pointer = 0;
		}

		else if (keycode == Key::enter) {
			
			cls();
			switch (pointer) {
			case 0:
				wczytaj();
				break;
			case 1:
				zapisz();
				break;
			case 2:
				przegladaj();
				break;
			case 3:
			{
				Search wys1;
				wys1.wyszukaj(&samochody);
				break;
			}
			case 4:
				dodaj();
				break;
			case 5:
				usun();
				break;
			case 6:
				consoleW();
				exit(0);
				break;
			}

			waitforkey();
		}
		
		else if (keycode == Key::esc) {
			exit(0);
		}

	}
}
