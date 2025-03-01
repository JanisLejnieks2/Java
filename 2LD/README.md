# Uzdevums:
Izstrādāt programmu, kas ievada no tastatūras taisnstūru skaitu un izmērus un izvada taisnstūru laukumus un perimetrus.
# Prasības programmai:
Izveidojiet failu ar nosaukumu Main.java
Izveidotā failā aprakstiet:
1) Abstrakto klasi Figura, kas satur divas abstraktās metodes: getArea un getPerimeter, abas metodes dot atpakaļ tipa double vērtību, abām metodēm parametru nav.
2) Klasi Rectangle, kas ir klases Figura pēctecis (metodēm getArea un getPerimeter ir jāaprēķina taisnstūra laukumu un perimetru). Bez tam klasei Rectangle ir jāsatur divus double tipa laukus width un height (platums un garums) un konstruktoru, doto lauku inicializācijai.
3) Publisko klasi Main, kas satur metodi main(). Metodē main izpildiet sekojošas darbības:
- Izveidojiet masīvu no elementiem tipa Figura. Elementu skaitu ir jāievada no tastatūras.
- Aizpildiet izveidoto masīvu ar klases Rectangle objektiem, taisnstūru izmērus ir jāievada no tastatūras. 
- Ar metodēm getArea un getPerimeter aprēķiniet visu taisnstūru laukumus un perimetrus un izvadiet tos ekrānā (laukumu no perimetra ir jāatdala ar atstarpi, informāciju par katru no taisnstūriem ir jāizvada jaunā rindā, reālus skaitļus izvadīt ar diviem cipariem aiz komata).
# Speciālās prasības programmai:
-	Programmas sākumā izvietot komentāru, kas satur informāciju par programmas izstrādātāju (studenta apliecības numurs, vārds, uzvārds, grupas numurs).
-	Ievades kļūdas atļauts neapstrādāt.
-	Pārbaudīt programmu ar testiem github.com portālā.