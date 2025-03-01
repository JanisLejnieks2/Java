# 7. Lab. darbs
Izpētiet programmu Main.java. Dotā programma izpilda sekojošas darbības:
* Izvada ekrānā izvēlni, kas satur piecas komandas: Create, Calculate, View, About un Exit un ievada no tastatūras komandas numuru.
* Ja lietotājs izvēlas komandu Create, tad programma veido failu Students.dat (tekošā mapē) un ieraksta dotā failā piecus klases Student objektus.
* Ja lietotājs izvēlas komandu View, tad programma izvada ekrānā faila Students.dat saturu.
* Ja lietotājs izvēlas komandu Exit, tad programma beidz savu darbu.
* Ja lietotājs izvēlas komandu Calculate vai About, tad programma izsauc klases Main metodi calculate vai metodi about. Dotās metodes neko nedara.

# Uzdevums:
Modificējiet metodes calculate un about tā, lai tie izpildītu sekojošas darbības:
* Metodei calculate ir jāievada no tastatūras studenta numurs (pieņemt, ka studenti ir sanumurēti no vieninieka) un jāmodificē failā Students.dat dotā studenta atzīmes. Jaunas atzīmes ievadīt no tastatūras. Ja tika ievadīts negatīvs studenta numurs vai numurs pārsniedz studentu skaitu failā, tad programmai ir jāizvada paziņojums "no such student".
* Metodei about ir jāizvada ziņas par programmas izstrādātāju (studenta apliecības numurs, vārds, uzvārds, grupa). 