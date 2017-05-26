import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Random;

public class KelimeOyunu {

	static Scanner girilenDeger;
	static int puan;

	static Random random = new Random();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		kullaniciGirisi();

	}

	public static void kullaniciGirisi() {

		String okunanDeger = "";
		boolean isNumeric;
		int sayi = 0;
		int secim;

		girilenDeger = new Scanner(System.in);

		DosyaIslemleri dIslem = new DosyaIslemleri();
		Kisi kisi = new Kisi();

		System.out.println("Adinizi giriniz : ");
		kisi.ad = girilenDeger.nextLine();
		System.out.println("Soyadinizi giriniz : ");
		kisi.soyAd = girilenDeger.nextLine();

		do {

			do {
				puan = 0;
				System.out.println("Kac kelime girmek istersiniz (0-100)?");
				okunanDeger = girilenDeger.nextLine();
				isNumeric = okunanDeger.chars().allMatch(Character::isDigit);
				if (isNumeric) {
					// System.out.println("numerik karakter");
					sayi = Integer.parseInt(okunanDeger);
				} else {
					// System.out.println("numerik degil");
					sayi = 120;
					continue;
				}

			} while (sayi > 100);

			do {
				System.out.println("Türkçe->Ýngilizce ...0 ");
				System.out.println("Ýngilizce->Türkçe ...1 ");
				okunanDeger = girilenDeger.nextLine();
				isNumeric = okunanDeger.chars().allMatch(Character::isDigit);
				if (isNumeric) {
					// System.out.println("numerik karakter");
					secim = Integer.parseInt(okunanDeger);
				} else {
					// System.out.println("numerik degil");
					secim = 2;
					continue;
				}

			} while (secim != 0 && secim != 1);

			randomOku(dIslem.sozlukOku("sozluk.txt"), secim, sayi);
			System.out.println("Tebrikler " + kisi.ad + " " + kisi.soyAd + " Puanýnýz: " + puan);
			System.out.println("Yeniden oynamak için lütfen '0' a týklayýnýz..");
			okunanDeger = girilenDeger.nextLine();
			isNumeric = okunanDeger.chars().allMatch(Character::isDigit);
			if (isNumeric)
				secim = Integer.parseInt(okunanDeger);
			else
				System.exit(0);

		} while (secim == 0);

	}

	public static void randomOku(String[][] dizi, int secim, int say) {
		int r;
		ArrayList<Integer> randomDizisi = new ArrayList<Integer>();

		if (secim == 0) {
			for (int i = 0; i < say; i++) {
				r = random.nextInt(5);
				if (randomDizisi.contains(r)) {
					// System.out.println("sayý bulundu");
					i--;
					continue;
				}
				randomDizisi.add(r);
				System.out.print(dizi[r][0].toString() + " kelimesinin Ingilizcesi :");
				if (dizi[r][1].equalsIgnoreCase(girilenDeger.nextLine())) {
					System.out.println("Tebrikler dogru bildiniz..");
					puan += 10;
				} else
					System.out.println("Yanlýþ cevap,dogrusu " + dizi[r][1].toString() + " olacaktý.");

			}
		} else if (secim == 1) {
			for (int i = 0; i < say; i++) {
				r = random.nextInt(5);
				if (randomDizisi.contains(r)) {
					System.out.println("sayý bulundu");
					i--;
					continue;
				}
				randomDizisi.add(r);
				System.out.print(dizi[r][1] + " kelimesinin Turkcesi :");
				if (dizi[r][0].equalsIgnoreCase(girilenDeger.nextLine())) {
					System.out.println("Tebrikler dogru bildiniz..");
					puan += 10;
				} else
					System.out.println("Yanlýþ cevap,dogrusu " + dizi[r][0].toString() + " olacaktý.");

			}

		}

	}

}

class DosyaIslemleri {
	String dosyaYolu;
	// String dosyaAdý;
	String[][] dizi = new String[100][2];

	public String[][] sozlukOku(String dosyaYolu) {
		try {
			File file = new File(dosyaYolu);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String satir;
			int j = 0;
			try {
				while ((satir = bufferedReader.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(satir);

					// her satýrý boþluklara göre bölme iþlemi
					while (st.hasMoreElements()) {
						for (int i = 0; i < 2; i++) {
							dizi[j][i] = st.nextElement().toString();
						}
						j++;
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return dizi;
	}

}

class Kisi {
	String ad;
	String soyAd;
	int puan;

	public Kisi() {
		this.ad = ad;
		this.soyAd = soyAd;
		this.puan = puan;

	}
}
