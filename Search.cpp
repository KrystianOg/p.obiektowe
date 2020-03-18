#include "Samochod.h"
#include "Search.h"
#include "menu.h"

Search::Search(std::vector<Samochod>& new_s)
{
	this->new_s = new_s;
}

void Search::print() {
	system("cls");
	consoleR();
	std::cout << "\t\tWYSZUKIWANIE\n\n";

	for (int i = 0; i < sh; i++) {
		if (pointer== i)
		{
			consoleB();
			std::cout << wyszukiwanie[i] << std::endl;
		}
		else
		{
			consoleW();
			std::cout << wyszukiwanie[i] << std::endl;
		}

	}

}

void Search::wyszukaj(std::vector<Samochod> *samochody) {

	Key keycode;

	while (true) {

		print();

		keycode = getchKey();

		if (keycode == Key::up) {
			pointer -= 1;

			if (pointer == -1)
				pointer = sh - 1;
		}

		else if (keycode == Key::down) {
			pointer += 1;

			if (pointer == sh)
				pointer = 0;
		}

		else if (keycode == Key::enter)
		{
			system("cls");
			switch (pointer) {
			case 0:
				wpoma();
				break;
			case 1:
				wpomo();
				break;
			case 2:
				wpor();
				break;
			case 3:
				wpoc();
				break;
			case 4:
				wpos();
				break;
			case 5:
				wpop();
				break;
			case 6:
				wpot();
				break;
			case 7:
				wyswietl(*samochody);
				break;
			case 8:
				return;
				break;
			}
		}

		else if (keycode == Key::esc) 
		{
			return;
		}
	}
}

void Search::wpoma() {
	std::cout << "\tPodaj marke: ";
	std::cin >> wpomarce;
	wpomarce_ = 1;
}

void Search::wpomo() {
	std::cout << "\tPodaj model: ";
	std::cin >> wpomodelu;
	wpomodelu_ = 1;
}

void Search::wpofunc(std::string od_, std::string do_, std::string podaj, int l, int r, bool ll, bool rr) {

	unsigned int point = 0;
	Key keycode;
	while (true) {
		system("cls");

		if (point == 0)
		{
			consoleB();
			std::cout << '\t' << od_;
			consoleW();
			std::cout << '\t' << do_;
		}

		else if (point == 1)
		{
			consoleW();
			std::cout << '\t' << od_;
			consoleB();
			std::cout << '\t' << do_;
		}

		consoleW();
		keycode = getchKey();

		if (keycode == Key::right || keycode == Key::left) {
			if (point == 0)
				point++;
			else if (point == 1)
				point--;
		}

		else if (keycode == Key::enter && point == 0) {
			std::cout << "\n\n\t" << podaj;
			std::cin >> l;
			ll = 1;

		}
		else if (keycode == Key::enter && point == 1) {
			std::cout << "\n\n\t" << podaj;
			std::cin >> r;
			rr = 1;

		}
		else if (keycode == Key::esc) {
			return;
		}
	}
}

void Search::wpofunc(std::string cos1, std::string cos2, bool *type, bool *type_) {

	unsigned int point = 0;
	Key keycode;
	while (true) {
		system("cls");

		if (point == 0)
		{
			consoleB();
			std::cout << '\t' << cos1;
			consoleW();
			std::cout << '\t' << cos2;
		}

		else if (point == 1)
		{
			consoleW();
			std::cout << '\t' << cos1;
			consoleB();
			std::cout << '\t' << cos2;
		}

		consoleW();
		keycode = getchKey();

		if (keycode == Key::right || keycode == Key::left) {
			if (point == 0)
				point++;
			else if (point == 1)
				point--;
		}

		else if (keycode == Key::enter && point == 0) {
			
			*type = 1;
			*type_ = 1;
			return;
		}

		else if (keycode == Key::enter && point == 1) {
			
			*type = 0;
			*type_ = 1;
			return;
		}

		else if (keycode == Key::esc) {
			return;

		}
	}
}

void Search::przegladaj() {
	int strona = 0;
	int iloscstr = new_s.size() % 15 + 1;

	int pozycja = 1;
	int size = new_s.size();

	Key keycode;

	while (true) {

		system("cls");
		for (int i = strona * 15; i < (strona + 1) * 15 && i < size; i++) {

			if (i == pozycja - 1)
				consoleB();
			else
				consoleW();

		//	std::cout << new_s.at(i).nr << ' ' << new_s.at(i).marka << ' ' << new_s.at(i).model << ' ' << new_s.at(i).rok << ' ' << new_s.at(i).cena << ' ' << new_s.at(i).stan << ' ' << new_s.at(i).przebieg << ' ' << new_s.at(i).typ_skrzyni;
			
			std::cout << new_s.at(i).nr << ' ' << new_s.at(i).marka << ' ' << new_s.at(i).model << ' ' << new_s.at(i).rok << ' ' << new_s.at(i).cena << ' ';
			if (new_s.at(i).stan == 1)
				std::cout << "Nowy" << ' ';
			else
				std::cout << "Uzywany" << ' ';

			std::cout << new_s.at(i).przebieg << ' ';

			if (new_s.at(i).typ_skrzyni == 1)
				std::cout << "Automatyczna" << ' ';
			else
				std::cout << "Manualna" << ' ';



			std::cout << '\n';
		}

		std::cout << "\n\n\tWybierz esc by wyjsc.";

		keycode = getchKey();

		if (keycode == Key::right && size > (strona + 1) * 15) {
			pozycja = (strona + 1) * 15 + 1;
			strona++;
		}


		else if (keycode == Key::left && strona > 0) {
			pozycja = (strona - 1) * 15 + 1;
			strona--;

		}

		else if (keycode == Key::up) {
			if (pozycja == strona * 15 + 1 && pozycja != 1) {
				strona--;
				pozycja--;
			}

			else if (pozycja == 1)
			{
			}

			else
				pozycja--;

		}

		else if (keycode == Key::down) {
			if (pozycja == (strona + 1) * 15 && pozycja != size) {
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
			system("cls");

		}

		else if (keycode == Key::esc)
			return;
	}

}

void Search::wpor() {

		std::string r[3] = { "Wyszukaj od roku: ","Wyszukaj do roku: ","Podaj rok: " };
		wpofunc(r[0], r[1], r[2], wporokub, wporokue, wporokub_, wporokue_);

}

void Search::wpoc() {

	std::string c[3] = { "Wyszukaj od ceny: ","Wyszukaj do ceny: ","Podaj cene: " };
	wpofunc(c[0],c[1],c[2], wpocenieb, wpoceniee, wpocenieb_, wpoceniee_);

}

void Search::wpos() {
	
	wpofunc("Nowy", "Uzywany", &wpostanie, &wpostanie_);
}

void Search::wpop() {

	std::string p[3] = { "Wyszukaj od przebiegu: ","Wyszukaj do przebiegu: ","Podaj przebieg: " };
	wpofunc(p[0], p[1], p[2], wpoprzb, wpoprze, wpoprzb_, wpoprze_);

}

void Search::wpot() {
	
	wpofunc("Automatyczna","Manualna",&wposkrz,&wposkrz_);
}

void Search::check(std::vector<Samochod> samochody) {

	new_s.clear();

	for (auto x : samochody) {
		if (wpomarce_==1) {
			if (wpomarce != x.marka)
				continue;
		}
		if (wpomodelu_ == 1) {
			if (wpomodelu != x.model)
				continue;
		}
		if (wporokub_ == 1) {
			if (x.rok<=wporokub)
				continue;
		}

		if (wporokue_ == 1) {
			if (x.rok >= wporokue)
				continue;
		}

		if (wpocenieb_ == 1) {
			if (x.cena<=wpocenieb) 
				continue;
		}

		if (wpoceniee_ == 1) {
			if (x.cena >= wpoceniee)
				continue;
		}
		
		if (wpostanie_ == 1) {
			if (x.stan != wpostanie)
				continue;
		}
		
		if (wpoprzb_ == 1) {
			if (x.przebieg <= wpoprzb)
				continue;
		}

		if (wpoprze_ == 1) {
			if (x.przebieg >=wpoprzb)
				continue;
		}
		
		if (wposkrz_ == 1) {
			if (x.typ_skrzyni != wposkrz)
				continue;
		}
		
		new_s.push_back({x.nr,x.marka,x.model,x.rok,x.cena,x.stan,x.przebieg,x.typ_skrzyni});
	}
}

void Search::wyswietl(std::vector<Samochod> samochody) {
	check(samochody);
	przegladaj();
}