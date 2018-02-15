package matti_p7;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Matti_P7 {
public static Map<String,Double> andmed = new HashMap<>();//tudengikoodi ja keskmise hinde hashmap
public static Map<String,Double> sorteeritud = new HashMap<>();//sorteeritud hinde järgi sama hashmap
public static double min=5, max=0;//min ja max leidmiseks deklareerin juba need vastupidiste väärtustega

    public static void main(String[] args) throws IOException {
        loemerida();//txt failist loetakse andmed hashmapi
        Scanner sc = new Scanner(System.in);
        int valik;//menüü valik
        
        do{
        System.out.println("1. Min. hinne ja nimekiri");
        System.out.println("2. Max. hinne ja nimekiri");
        System.out.println("3. Tudengite andmed aasta kaupa");
        System.out.println("4. Tudengite koodid, kelle hinded olid sisestatud vahemikus");
        System.out.println("5. Välju");
        valik=sc.nextInt();//menüü valik
        kirjutamefaili(valik);//meetod, mis registreerib kasutaja valitud menüü kellaajaga
        if(valik==5){//et pärast valikut jõuaks registreerida kasutaja valiku andmed2.txt faili
            valik=6;//kui valik võrdub 6ga siis do tsükkel lõppeb
        }
        switch(valik){//switch menüü järgi
        //min
            case 1: {//valik 1
                minjamax();//miinimum ja maksimum leidmine
            
                System.out.println("Min hinne: "+min+"\nTudengid:");//miinimum hinne leiti ja prinditakse koos tekstiga
                for (String name: andmed.keySet()){//tsükkel kestab nii palju kui palju on mapis paare
                    if(andmed.get(name)==min){//kui leiti miinimumhinne, siis prinditakse tudengikood
                        System.out.println(name);
                    }
                }
            }break;
            case 2: {//valik 2
        //max
                minjamax();//miinimum ja maksimum leidmine
                System.out.println("Max hinne: "+max+"\nTudengid:");//maksimum hinne leiti ja prinditakse koos tekstiga
                for (String name: andmed.keySet()){//tsükkel kestab nii palju kui palju on mapis paare
                    if(andmed.get(name)==max){//kui leiti maksimumhinne, siis prinditakse tudengikood
                        System.out.println(name);
                    }
                }
            }break;
            case 3: {//valik 3
        //sisestatud sisseastumise aasta tudengite andmed
                System.out.println("Sisestage aasta");//kasutaja peab sisestama aasta
                String aasta = sc.next();//sisestati näiteks 2016
                keskminehinne(aasta);//meetod prindib kõik andmed, kelle tudengikood algab 16'ga
            }break;
            case 4: {
        //kuva tudengite koodid ja hinded, kelle oppeedukus oli vahemikus ..kuni..
                System.out.println("Alates..");
                int alates=sc.nextInt();//kasutaja sisestab alg numbri
                System.out.println("kuni..");
                int kuni=sc.nextInt();//kasutaja sisestab lõpp numbri
                kuvavahemikus(alates,kuni);//meetod tagastab andmed, kelle hinded olid vahemikus näiteks 2 kuni 4
            }break;
                    
    }
        } while(valik!=6);//kui valik = 6, siis do tsükkel lõppeb
    }
    public static void loemerida() throws FileNotFoundException, IOException{//meetod, mis loob andmed.txt hashmapiks
        File f=new File("C:\\Users\\karlm\\Documents\\NetBeansProjects\\Matti_P7\\src\\matti_p7\\andmed.txt");//andmed.txt asukoht ja faili f deklareerimine
        FileReader fr=new FileReader(f);//faili lugemise klassi deklareerimine
        BufferedReader reader=new BufferedReader(fr);//loeb tekstifaile, mille sisuks on characters
        String failirida;

        while((failirida=reader.readLine())!=null)//tsükkel kestab kuni failis on ridu
        {//failirida saab omale rea väärtuse stringina
            
            String[]rida=failirida.split(" ");//rida massiivi pannakse andmed ja kus on tühik, sealt eraldadakse järgmine string

            if(rida[0].length()>0)//kui real oli mingid väärtused,siis
                andmed.put(rida[0], new Double(rida[1]));//sisestatakse tudengikood ja keskmine hinne hashmapi
        }

    }
    public static void minjamax(){//min ja max leidmine
        
        for (String name: andmed.keySet()){//for tsükkel kestab kuni on hashmapis paare
            
            String key =name;//võtme väärtuse deklareerimine
            Double value = andmed.get(name);//hinde deklareerimine  
            if(value<min)min=value;//kui keskmine hinne on väiksem min väärtusest siis min saab uue väärtuse
            if(value>max)max=value;//kui keskmine hinne on suurem max väärtusest siis max saab uue väärtuse
        }
    }
    public static void keskminehinne(String x){//keskmise hinde leidmine sama aasta tudengite järgi
        double keskmine=0;//keskmine saab double väärtuse, sest keskmiseid hinded on enamus komaga
        int mitmega=0;//loendab mitu tudengit sellel aastal õppis
        System.out.printf(x + ". aasta tudengite nimekiri:");//x ehk sisestatud aasta
        for (String name: andmed.keySet()){//for tsükkel kestab kuni on hashmapis paare
            
            String key =name;//võtme väärtuse deklareerimine
            Double value = andmed.get(name); //hinde deklareerimine
            if(key.charAt(1)==x.charAt(2)&&key.charAt(2)==x.charAt(3)){//kui tudengi koodi teine ja kolmas sümbol võrdub 
                //sisestatud aasta kolmanda ja neljanda sümboliga siis 
                System.out.printf("\t"+key);//tudengite nimekirja printimine
                keskmine+=value;//x aasta tudengite keskmiste hinnete liitmine
                mitmega++;//tudengite lugemine
            }
            
        }
        keskmine=keskmine/mitmega;//hinnete summa jagatakse õpilaste arvuga ja leitakse keskmine
        System.out.println();
        System.out.println("Keskmine hinne "+x+". aasta tudengite seas on "+keskmine);//teksti välja printimine leitud väärtustega
    }
    public static void kuvavahemikus(int from, int to){//tudengi andmete printimine kelle hinne jääb sisestatud vahemikku

        for (String name: andmed.keySet()){//tsükkel kestab nii kaua kui palju on hashmapis paare
            
            String key = name;
            Double value = andmed.get(name);  
            //siin meetodis tehakse hashmap, kuhu pannakse ainult tudengid, kelle hinne jääb sisestatud vahemikku
            //seejärel hakatakse seda sorteerima teises meetodis ja prinditakse välja
            if(value>=from&&value<=to){//kui hinne jääb sisestatud vahemikku siis lisatakse see sorteeritud hashmapi
               
                sorteeritud.put(key, value);
            }
        }
        System.out.println(sortByValue(sorteeritud));//prinditakse välja sorteeritud hashmap
    }
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {//staatiline meetod, mille muutujaks on map ja tagastab uuendatud mapi

    //https://www.mkyong.com/java/how-to-sort-a-map-in-java/
    List<Map.Entry<K, V>> list;//list ehk järjestus, mis on üks kollektsioonidest
    list = new LinkedList<>(unsortMap.entrySet());//unsortmap ehk "sorteeritud" hashmap, list deklareerimine
    //collections.sort sorteerib alfabeetilises või numbrilises järjekorras elemente
    Collections.sort(list, (Map.Entry<K, V> o1, Map.Entry<K, V> o2) -> (o1.getValue()).compareTo(o2.getValue()));

    Map<K, V> result = new LinkedHashMap<>();//result deklareerimine hashmapiks
    for (Map.Entry<K, V> entry : list) {//tsükkel kestab nii palju kui on paare list'is
        result.put(entry.getKey(), entry.getValue());//result mapi väärtuste lisamine
    }

    return result;//tagastatakse linkedhashmap

    }
    public static void kirjutamefaili(int menu) throws IOException{//meetod mis loob vajadusel uue txt faili
        //ja sisestab sinna kasutaja valikud aegadega
        File f=new File("C:\\Users\\karlm\\Documents\\NetBeansProjects\\Matti_P7\\src\\matti_p7\\andmed2.txt");//file f loetakse antud aadressilt
        FileWriter fwr=new FileWriter(f,true);//kui faili kirjutatakse siis ei kirjutata faili üle vaid lisatakse teksti juurde
   
        if(!f.exists()){ //kui faili ei ole olemas siis luuakse eelnevalt toodud asukohale
            f.createNewFile();
        }
        Date d=new Date();//d saab hetke aja väärtuse
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");//formaat, kuidas aeg txt faili sisestatakse
        fwr.write(format1.format(d)+" "+menu+"\n");//teksti sisestatakse formaadiga aeg ja sisestatud menüü number
        fwr.flush();//ajutises mälus käskude täitmine ja mälu tühjendamine
    }
}
