
package matti_p8;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Matti_P8 {
        public static File inputFile=new File("C:\\Users\\karlm\\Documents\\NetBeansProjects\\Matti_P8\\src\\matti_p8\\books.xml");//inputFile saab omale faili v22rtuse "" vahel olevast asukohast
        public static DocumentBuilderFactory dBuilder=DocumentBuilderFactory.newInstance();//Määratleb muutuja API, mis võimaldab rakendustel saada parserit, mis toodab XML-dokumentides DOM-i object trees
        public static Document doc;//sisestatud faili esindaja, mis on documentbuilderfactory funktsiooniga lahti parsetatud
        public static NodeList nList;//node kollektsioon ehk mingite kategooriate v6i gruppide kollektsioon
        public static DocumentBuilder docb;//documentbuilder defineerib API saama DOM dokumenti XML dokumendist

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        docb=dBuilder.newDocumentBuilder();//uue juhtumi loomine documentbuilderile kasutades praeguseid parameetreid
        doc=docb.parse(inputFile);//inputFile xml dokumendina esitamine ja dom dokumendi objektiks teisendamine
        doc.getDocumentElement().normalize();//kontrollib, et teie andmed oleksid realiseeritud 6ige xml formaadis
        
        Scanner sc=new Scanner(System.in);
        int valik;
        do{//tsykkel kestab nii kaua kuni valik v6rdub 6ga
            System.out.println("Valige number vahemikus 1..6");
            System.out.println("1. Sisestatud ID raamatu info");
            System.out.println("2. Raamatud, kus hind on alates..kuni..");
            System.out.println("3. Sisestatud osakonna raamatud");
            System.out.println("4. Sisestatud autori raamatud");
            System.out.println("5. Valitud raamatu hinna muutmine");
            System.out.println("6. Sulge");
            valik=sc.nextInt();//kasutaja sisestab valiku
            
            switch(valik){//kasutaja menyy valiku j2rgi toimib switch
                case 1:{
                    System.out.println("Sisestage raamatu ID(naiteks bk111)");
                    String id=sc.next();//kasutaja sisestab id
                    raamatuInfo(id);//aktiveeritakse meetod, milles on kasutaja sisestatud meetod
                }break;
                case 2:{
                    System.out.println("Sisestage alates(naiteks 4)");
                    double alates=sc.nextInt();
                    System.out.println("Sisestage kuni(naiteks 5)");
                    double kuni=sc.nextInt();
                    raamatudVahemikus(alates,kuni);// raamatud kasutaja sisestatud hinnavahemikus
                }break;
                case 3:{
                    System.out.println("Sisestage raamatu osakond(naiteks Fantasy)");
                    String genre=sc.next();
                    raamatudOsakonnas(genre);//raamatud kasutaja sisestatud osakonnas
                }break;
                case 4:{
                    System.out.println("Sisestage raamatu autor(naiteks Corets, Eva)");
                    String autor=sc.next();
                    raamatudAutori(autor);//kasutaja sisestatud autorite raamatud
                }break;
                case 5:{
                    System.out.println("Sisestage raamatu ID(naiteks bk111)");
                    String id=sc.next();
                    raamatuInfo(id);//kasutaja sisestab raamatu id ja siis kuvatakse talle raamatu info
                    System.out.println("Sisestage raamatu uus hind(naiteks 6)");
                    String uushind=sc.next();//kasutaja sisestab uue hinna
                    hinnaMuutmine(id, uushind);//hinna muutmisel kasutatakse sisestatud raamatu id ja uut hinda
                }break;
            }
        }while(valik!=6);//kestab kuni kasutaja valib peamenyys 6
        
    }
    public static void raamatuInfo(String id) throws ParserConfigurationException, SAXException, IOException{
        nList=doc.getElementsByTagName("book");//book kategooria deklareerimine
        for(int i=0;i<nList.getLength();i++)//kestab nii palju kui on raamatuid
        {
        Node nNode=nList.item(i);//nNode saab omale i raamatu info
            if(nNode.getNodeType()==Node.ELEMENT_NODE)//node tyybi ja node elemendi v6rdlemine
                
            {
                Element eElement=(Element)nNode;//eElementi j2rgi saab booki alamkategoriaid esile tuua
                
                if(eElement.getAttribute("id").equals(id)){//kui book kategooria atribuut id v6rdub kasutaja sisestatud idga
                    //eElement.getElementsByTagName("author") otsib antud raamatu <author></author> vahel oleva informatsiooni
                    //item(0) n2itab et voetakse esimene esimene objekt
                    //getTextContent n2itab et tagastatakse stringina
                    System.out.println("Autor: "+ eElement.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Pealkiri: "+ eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Zanr: "+ eElement.getElementsByTagName("genre").item(0).getTextContent());
                    System.out.println("Hind: "+ eElement.getElementsByTagName("price").item(0).getTextContent());
                    System.out.println("Avalikustamise aeg: "+ eElement.getElementsByTagName("publish_date").item(0).getTextContent());
                    System.out.println("Kirjeldus: "+ eElement.getElementsByTagName("description").item(0).getTextContent());
                } 
            }
        }
    }
    public static void raamatudVahemikus(double from, double to) throws ParserConfigurationException{
        nList=doc.getElementsByTagName("book");
        for(int i=0;i<nList.getLength();i++)
        {
            Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE)
            {
                Element eElement=(Element)nNode;
                //deklareerin doubleks et komakohaga arvude v22rtused saada
                //Double.parseDoublega saame string muuta double v22rtuseks
                //v6tame hinna string v22rtuse <price></price> tagide vahelt
                double value = Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent());
                
                if(value>=from && value<=to)//hind peab olema kasutaja sisestatud vahemikus ja siis kuvatakse raamatu andmed
                {
                System.out.println("Autor: "+ eElement.getElementsByTagName("author").item(0).getTextContent());
                System.out.println("Pealkiri: "+ eElement.getElementsByTagName("title").item(0).getTextContent());
                System.out.println("Zanr: "+ eElement.getElementsByTagName("genre").item(0).getTextContent());
                System.out.println("Hind: "+ eElement.getElementsByTagName("price").item(0).getTextContent());
                System.out.println("Avalikustamise aeg: "+ eElement.getElementsByTagName("publish_date").item(0).getTextContent());
                System.out.println("Kirjeldus: "+ eElement.getElementsByTagName("description").item(0).getTextContent());
                }
            }
        }
    }
    public static void raamatudOsakonnas(String osakond){
        nList=doc.getElementsByTagName("book");
        for(int i=0;i<nList.getLength();i++)
        {
        Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE)
            {
                Element eElement=(Element)nNode;
                if(eElement.getElementsByTagName("genre").item(0).getTextContent().equals(osakond)){//kui info <genre></genre> vahel v6rdub sisestatud osakonnaga siis toimub j2rgnev
                    System.out.println("Autor: "+ eElement.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Pealkiri: "+ eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Zanr: "+ eElement.getElementsByTagName("genre").item(0).getTextContent());
                    System.out.println("Hind: "+ eElement.getElementsByTagName("price").item(0).getTextContent());
                    System.out.println("Avalikustamise aeg: "+ eElement.getElementsByTagName("publish_date").item(0).getTextContent());
                    System.out.println("Kirjeldus: "+ eElement.getElementsByTagName("description").item(0).getTextContent());
                } 
            }
        }
    }
    public static void raamatudAutori(String autor){
        nList=doc.getElementsByTagName("book");
        for(int i=0;i<nList.getLength();i++)
        {
        Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE)
            {
                Element eElement=(Element)nNode;
                if(eElement.getElementsByTagName("author").item(0).getTextContent().equals(autor)){//kui info <author></author> vahel v6rdub sisestatud autoriga siis toimub j2rgnev
                    System.out.println("Autor: "+ eElement.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Pealkiri: "+ eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Zanr: "+ eElement.getElementsByTagName("genre").item(0).getTextContent());
                    System.out.println("Hind: "+ eElement.getElementsByTagName("price").item(0).getTextContent());
                    System.out.println("Avalikustamise aeg: "+ eElement.getElementsByTagName("publish_date").item(0).getTextContent());
                    System.out.println("Kirjeldus: "+ eElement.getElementsByTagName("description").item(0).getTextContent());
                } 
            }
        }
    }
    public static void hinnaMuutmine(String id, String hind) throws TransformerConfigurationException, TransformerException{
 
    nList=doc.getElementsByTagName("book");
        for(int i=0;i<nList.getLength();i++)
        {
        Node nNode=nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE)
            {
                Element eElement=(Element)nNode;
                if(eElement.getAttribute("id").equals(id)){
                    eElement.getElementsByTagName("price").item(0).setTextContent(hind);//setTextContent t2hendab, et kogu info asendatakse "hind" v22rtusega
                    } 
            }
        }
     // failis muutmine
    TransformerFactory transFactory=TransformerFactory.newInstance();//.newInstance on transformerfactory uus juhtum
    Transformer trans=transFactory.newTransformer();//failiks muutmise klass
    
    DOMSource source =new DOMSource(doc);//transformeeritud Source tree hoidmine Document Object Model Source vormis 
    StreamResult result=new StreamResult(new File("C:\\Users\\karlm\\Documents\\NetBeansProjects\\Matti_P8\\src\\matti_p8\\books.xml"));//faili asukoht mida hakatakse yle lugema
    trans.transform(source, result);//failiks muutmise valem kus voetakse algasukoha fail ja asendatakse muudetud xmliga
    
    
    }
}
