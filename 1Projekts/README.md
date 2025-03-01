# Uzdevums
Nukleotīdi, kas veido gēnus DNS molekulās, tiek apzīmēti ar vienu no četrām vērtībām: A, C, G vai T. Ja gēna apraksts tiks saglabāts mainīgā tipa String, tad katra nukleotīga kodēšanai būs nepieciešami divi baiti, jeb 16 biti, jo String tipa virknēs valodā Java sastāv no Unicode simboliem. Taču četru iespējamo vērtību kodēšanai var izmantot divus bitus, piemēram, A iekodēt ar bitiem 00, C ar bitiem 01, G ar 10, bet T ar 11. Tad atmiņas apjoms, kas ir vajadzīgs gēna apraksta glabāšanai, var būt samazināts 8 reizes (no 16 bitiem līdz 2 bitiem katra nukleotīda kodēšanai). 

# Prasības programmai
- programma ir jāizstrādā kā konsoles lietotne
- programmā jāiekļauj lietotāja nepareizu darbību apstrāde
- Programmas pirmkodu izvietot vienā failā ar nosaukumu Main.java
- Failā Main.java sākumā ievietot komentāru, kas satur informāciju par programmas autoru (apliecības numurs, vārds, uzvārds, grupa)