package mx.com.underrito.metro.free.globalvalues;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.SparseArray;
import mx.com.underrito.metro.free.R;
import mx.com.underrito.metro.free.constant.MetroConstant;
import mx.com.underrito.metro.free.model.MetroJbEstacion;
import mx.com.underrito.metro.free.model.MetroJbLinea;
import mx.com.underrito.metro.free.model.MetroJbSegmento;
import mx.com.underrito.metro.free.util.MetroLineaEstacionPopulator;
public class MetroRed
{
	private ArrayList<MetroJbSegmento> segmentos;
	private SparseArray<MetroJbEstacion> mapaEstacion;
	private MetroJbLinea linea1;
	private MetroJbLinea linea2;
	private MetroJbLinea linea3;
	private MetroJbLinea linea4;
	private MetroJbLinea linea5;
	private MetroJbLinea linea6;
	private MetroJbLinea linea7;
	private MetroJbLinea linea8;
	private MetroJbLinea linea9;
	private MetroJbLinea lineaA;
	private MetroJbLinea lineaB;
	private MetroJbLinea linea12;
	private ArrayList<MetroJbLinea> listaLinea;
	public static MetroRed metroJbRed;
	//private Context baseContext;
	//private Context appplicationContext;

	public List<MetroJbEstacion>getListaEstacionList() 
	{
		List<MetroJbEstacion>listaEstacionList= new ArrayList<MetroJbEstacion>();
		for(int i=0;i<this.mapaEstacion.size();i++)
			listaEstacionList.add(this.mapaEstacion.get(i));
		return listaEstacionList;
	}
	public void reload()
	{
		//Log.i("ROD","RELOAD:"+((MetroGlobalValues)appplicationContext).getMetroUtil().getSharedPreferences(this.baseContext).getBoolean(MetroConstant.PREFERENCIA_LINEA12,false));
		this.init();
	}
	public MetroRed(Context appplicationContext,Context baseContext)
	{
	//	this.baseContext						= baseContext;
		//	this.appplicationContext				= appplicationContext;
		this.init();
	}
	private void init() 
	{
		segmentos 				= new ArrayList<MetroJbSegmento>();
		mapaEstacion 			= new SparseArray<MetroJbEstacion>();
		this.linea1 			= new MetroJbLinea();
		this.linea2 			= new MetroJbLinea();
		this.linea3 			= new MetroJbLinea();
		this.linea4 			= new MetroJbLinea();
		this.linea5 			= new MetroJbLinea();
		this.linea6 			= new MetroJbLinea();
		this.linea7 			= new MetroJbLinea();
		this.linea8 			= new MetroJbLinea();
		this.linea9 			= new MetroJbLinea();
		this.lineaA 			= new MetroJbLinea();
		this.lineaB 			= new MetroJbLinea();
		this.linea12 			= new MetroJbLinea();
		MetroLineaEstacionPopulator.cargaDatoEstacion(this);
		this.listaLinea = new ArrayList<MetroJbLinea>();
		this.listaLinea.add(this.getLinea1());
		this.listaLinea.add(this.getLinea2());
		this.listaLinea.add(this.getLinea3());
		this.listaLinea.add(this.getLinea4());
		this.listaLinea.add(this.getLinea5());
		this.listaLinea.add(this.getLinea6());
		this.listaLinea.add(this.getLinea7());
		this.listaLinea.add(this.getLinea8());
		this.listaLinea.add(this.getLinea9());
		this.listaLinea.add(this.getLineaA());
		this.listaLinea.add(this.getLineaB());
		//if(((MetroGlobalValues)appplicationContext).getMetroUtil().getSharedPreferences(this.baseContext).getBoolean(MetroConstant.PREFERENCIA_LINEA12,false))
			this.listaLinea.add(this.getLinea12());		
		//MetroLineaEstacionPopulator.cargaEstacionLinea(this);
		MetroLineaEstacionPopulator.setSegmentoPeso(this);
	}

	public MetroJbLinea getLinea1() {
		this.linea1 = new MetroJbLinea();
		this.linea1.setColor("rosa");
		this.linea1.setLongitud(16.654);		
		this.linea1.setNombre("Linea 1");
		this.linea1.setId("1");
		this.linea1.setColorHex("#E45F91");
		this.linea1.setImage(R.drawable.linea1);
		this.linea1.setInicioTag(MetroConstant.VERTICAL_IZQUIERDO);
		this.linea1.setFinalTag(MetroConstant.HORIZONTAL_ARRIBA);		
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.OBSERVATORIO_ID);
		listaEstacionNumero.add(MetroConstant.TACUBAYA_ID);
		listaEstacionNumero.add(MetroConstant.JUANACATLAN_ID);
		listaEstacionNumero.add(MetroConstant.CHAPULTEPEC_ID);
		listaEstacionNumero.add(MetroConstant.SEVILLA_ID);
		listaEstacionNumero.add(MetroConstant.INSURGENTES_ID);
		listaEstacionNumero.add(MetroConstant.CUAUHTEMOC_ID);
		listaEstacionNumero.add(MetroConstant.BALDERAS_ID);
		listaEstacionNumero.add(MetroConstant.SALTO_DEL_AGUA_ID);
		listaEstacionNumero.add(MetroConstant.ISABEL_LA_CATOLICA_ID);
		listaEstacionNumero.add(MetroConstant.PINO_SUAREZ_ID);
		listaEstacionNumero.add(MetroConstant.MERCED_ID);
		listaEstacionNumero.add(MetroConstant.CANDELARIA_ID);
		listaEstacionNumero.add(MetroConstant.SAN_LAZARO_ID);
		listaEstacionNumero.add(MetroConstant.MOCTEZUMA_ID);
		listaEstacionNumero.add(MetroConstant.BALBUENA_ID);
		listaEstacionNumero.add(MetroConstant.BOULEVARD_PUERTO_AEREO_ID);
		listaEstacionNumero.add(MetroConstant.GOMEZ_FARIAS_ID);
		listaEstacionNumero.add(MetroConstant.ZARAGOZA_ID);
		listaEstacionNumero.add(MetroConstant.PANTITLAN_ID);
		this.linea1.setListaEstacionNumero(listaEstacionNumero);
		return this.linea1;
	}

	public MetroJbLinea getLinea12() {
		this.linea12 = new MetroJbLinea();
		this.linea12.setColor("dorado");
		this.linea12.setNombre("Linea 12");
		this.linea12.setId("12");
		this.linea12.setColorHex("#BF9B50");	
		this.linea12.setLongitud(20.278);		
		this.linea12.setImage(R.drawable.linea12);
		this.linea12.setInicioTag(MetroConstant.VERTICAL_DERECHO);
		this.linea12.setFinalTag(MetroConstant.VERTICAL_IZQUIERDO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.TLAHUAC_ID);
		listaEstacionNumero.add(MetroConstant.TLALTENCO_ID);
		listaEstacionNumero.add(MetroConstant.ZAPOTITLAN_ID);
		listaEstacionNumero.add(MetroConstant.NOPALERA_ID);
		listaEstacionNumero.add(MetroConstant.OLIVOS_ID);
		listaEstacionNumero.add(MetroConstant.TEZONCO_ID);
		listaEstacionNumero.add(MetroConstant.PERIFERICO_ORIENTE_ID);
		listaEstacionNumero.add(MetroConstant.CALLE_11_ID);
		listaEstacionNumero.add(MetroConstant.LOMAS_ESTRELLA_ID);
		listaEstacionNumero.add(MetroConstant.SAN_ANDRES_TOMATLAN_ID);
		listaEstacionNumero.add(MetroConstant.CULHUACAN_ID);
		listaEstacionNumero.add(MetroConstant.ATLALILCO_ID);
		listaEstacionNumero.add(MetroConstant.MEXICALTZINGO_ID);
		listaEstacionNumero.add(MetroConstant.ERMITA_ID);
		listaEstacionNumero.add(MetroConstant.EJE_CENTRAL_ID);
		listaEstacionNumero.add(MetroConstant.PARQUE_DE_LOS_VENADOS_ID);
		listaEstacionNumero.add(MetroConstant.ZAPATA_ID);
		listaEstacionNumero.add(MetroConstant.HOSPITAL_20_DE_NOVIEMBRE_ID);
		listaEstacionNumero.add(MetroConstant.INSURGENTES_SUR_ID);
		listaEstacionNumero.add(MetroConstant.MIXCOAC_ID);
		this.linea12.setListaEstacionNumero(listaEstacionNumero);
		return this.linea12;
	}

	public MetroJbLinea getLinea2() {
		this.linea2 = new MetroJbLinea();
		this.linea2.setColor("azul");
		this.linea2.setNombre("Linea 2");
		this.linea2.setColorHex("#0B6DAD");
		this.linea2.setId("2");		
		this.linea2.setImage(R.drawable.linea2);
		this.linea2.setLongitud(20.713);
		this.linea2.setInicioTag(MetroConstant.VERTICAL_IZQUIERDO);
		this.linea2.setFinalTag(MetroConstant.HORIZONTAL_ABAJO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.CUATRO_CAMINOS_ID);
		listaEstacionNumero.add(MetroConstant.PANTEONES_ID);
		listaEstacionNumero.add(MetroConstant.TACUBA_ID);
		listaEstacionNumero.add(MetroConstant.CUITLAHUAC_ID);
		listaEstacionNumero.add(MetroConstant.POPOTLA_ID);
		listaEstacionNumero.add(MetroConstant.COLEGIO_MILITAR_ID);
		listaEstacionNumero.add(MetroConstant.NORMAL_ID);
		listaEstacionNumero.add(MetroConstant.SAN_COSME_ID);
		listaEstacionNumero.add(MetroConstant.REVOLUCION_ID);
		listaEstacionNumero.add(MetroConstant.HIDALGO_ID);
		listaEstacionNumero.add(MetroConstant.BELLAS_ARTES_ID);
		listaEstacionNumero.add(MetroConstant.ALLENDE_ID);
		listaEstacionNumero.add(MetroConstant.ZOCALO_ID);
		listaEstacionNumero.add(MetroConstant.PINO_SUAREZ_ID);
		listaEstacionNumero.add(MetroConstant.SAN_ANTONIO_ABAD_ID);
		listaEstacionNumero.add(MetroConstant.CHABACANO_ID);
		listaEstacionNumero.add(MetroConstant.VIADUCTO_ID);
		listaEstacionNumero.add(MetroConstant.XOLA_ID);
		listaEstacionNumero.add(MetroConstant.VILLA_DE_CORTES_ID);
		listaEstacionNumero.add(MetroConstant.NATIVITAS_ID);
		listaEstacionNumero.add(MetroConstant.PORTALES_ID);
		listaEstacionNumero.add(MetroConstant.ERMITA_ID);
		listaEstacionNumero.add(MetroConstant.GENERAL_ANAYA_ID);
		listaEstacionNumero.add(MetroConstant.TASQUENA_ID);
		this.linea2.setListaEstacionNumero(listaEstacionNumero);
		return this.linea2;
	}

	public MetroJbLinea getLinea3() {
		this.linea3 = new MetroJbLinea();
		this.linea3.setColor("verde olivo");
		this.linea3.setNombre("Linea 3");
		this.linea3.setColorHex("#AE9D27");
		this.linea3.setId("3");
		this.linea3.setImage(R.drawable.linea3);
		this.linea3.setLongitud(21.278);
		this.linea3.setInicioTag(MetroConstant.HORIZONTAL_ARRIBA);
		this.linea3.setFinalTag(MetroConstant.HORIZONTAL_ABAJO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.INDIOS_VERDES_ID);
		listaEstacionNumero.add(MetroConstant.DEPORTIVO_18_DE_MARZO_ID);
		listaEstacionNumero.add(MetroConstant.POTRERO_ID);
		listaEstacionNumero.add(MetroConstant.LA_RAZA_ID);
		listaEstacionNumero.add(MetroConstant.TLATELOLCO_ID);
		listaEstacionNumero.add(MetroConstant.GUERRERO_ID);
		listaEstacionNumero.add(MetroConstant.HIDALGO_ID);
		listaEstacionNumero.add(MetroConstant.JUAREZ_ID);
		listaEstacionNumero.add(MetroConstant.BALDERAS_ID);
		listaEstacionNumero.add(MetroConstant.NINOS_HEROES_ID);
		listaEstacionNumero.add(MetroConstant.HOSPITAL_GENERAL_ID);
		listaEstacionNumero.add(MetroConstant.CENTRO_MEDICO_ID);
		listaEstacionNumero.add(MetroConstant.ETIOPIA_PLAZA_DE_LA_TRANSPARENCIA_ID);
		listaEstacionNumero.add(MetroConstant.EUGENIA_ID);
		listaEstacionNumero.add(MetroConstant.DIVISION_DEL_NORTE_ID);
		listaEstacionNumero.add(MetroConstant.ZAPATA_ID);
		listaEstacionNumero.add(MetroConstant.COYOACAN_ID);
		listaEstacionNumero.add(MetroConstant.VIVEROS_DERECHOS_HUMANOS_ID);
		listaEstacionNumero.add(MetroConstant.MIGUEL_ANGEL_DE_QUEVEDO_ID);
		listaEstacionNumero.add(MetroConstant.COPILCO_ID);
		listaEstacionNumero.add(MetroConstant.UNIVERSIDAD_ID);
		this.linea3.setListaEstacionNumero(listaEstacionNumero);
		return this.linea3;
	}

	public MetroJbLinea getLinea4() {
		this.linea4 = new MetroJbLinea();
		this.linea4.setColor("cyan");
		this.linea4.setNombre("Linea 4");
		this.linea4.setColorHex("#73C4B8");
		this.linea4.setId("4");
		this.linea4.setImage(R.drawable.linea4);
		this.linea4.setLongitud(9.363);
		this.linea4.setInicioTag(MetroConstant.HORIZONTAL_ARRIBA);
		this.linea4.setFinalTag(MetroConstant.HORIZONTAL_ABAJO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.MARTIN_CARRERA_ID);
		listaEstacionNumero.add(MetroConstant.TALISMAN_ID);
		listaEstacionNumero.add(MetroConstant.BONDOJITO_ID);
		listaEstacionNumero.add(MetroConstant.CONSULADO_ID);
		listaEstacionNumero.add(MetroConstant.CANAL_DEL_NORTE_ID);
		listaEstacionNumero.add(MetroConstant.MORELOS_ID);
		listaEstacionNumero.add(MetroConstant.CANDELARIA_ID);
		listaEstacionNumero.add(MetroConstant.FRAY_SERVANDO_ID);
		listaEstacionNumero.add(MetroConstant.JAMAICA_ID);
		listaEstacionNumero.add(MetroConstant.SANTA_ANITA_ID);
		this.linea4.setListaEstacionNumero(listaEstacionNumero);
		return this.linea4;
	}

	public MetroJbLinea getLinea5() {
		this.linea5 = new MetroJbLinea();
		this.linea5.setColor("amarillo");
		this.linea5.setNombre("Linea 5");
		this.linea5.setColorHex("#FEBF26");
		this.linea5.setId("5");
		this.linea5.setImage(R.drawable.linea5);
		this.linea5.setLongitud(14.435);	
		this.linea5.setInicioTag(MetroConstant.VERTICAL_IZQUIERDO);
		this.linea5.setFinalTag(MetroConstant.HORIZONTAL_ARRIBA);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.PANTITLAN_ID);
		listaEstacionNumero.add(MetroConstant.HANGARES_ID);
		listaEstacionNumero.add(MetroConstant.TERMINAL_AEREA_ID);
		listaEstacionNumero.add(MetroConstant.OCEANIA_ID);
		listaEstacionNumero.add(MetroConstant.ARAGON_ID);
		listaEstacionNumero.add(MetroConstant.EDUARDO_MOLINA_ID);
		listaEstacionNumero.add(MetroConstant.CONSULADO_ID);
		listaEstacionNumero.add(MetroConstant.VALLE_GOMEZ_ID);
		listaEstacionNumero.add(MetroConstant.MISTERIOS_ID);
		listaEstacionNumero.add(MetroConstant.LA_RAZA_ID);
		listaEstacionNumero.add(MetroConstant.AUTOBUSES_DEL_NORTE_ID);
		listaEstacionNumero.add(MetroConstant.INSTITUTO_DEL_PETROLEO_ID);
		listaEstacionNumero.add(MetroConstant.POLITECNICO_ID);
		this.linea5.setListaEstacionNumero(listaEstacionNumero);
		return this.linea5;
	}

	public MetroJbLinea getLinea6() {
		this.linea6 = new MetroJbLinea();
		this.linea6.setColor("roja");
		this.linea6.setNombre("Linea 6");
		this.linea6.setColorHex("#CF2038");
		this.linea6.setId("6");
		this.linea6.setLongitud(11.434);		
		this.linea6.setImage(R.drawable.linea6);
		this.linea6.setInicioTag(MetroConstant.VERTICAL_IZQUIERDO);
		this.linea6.setFinalTag(MetroConstant.VERTICAL_DERECHO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.EL_ROSARIO_ID);
		listaEstacionNumero.add(MetroConstant.TEZOZOMOC_ID);
		listaEstacionNumero.add(MetroConstant.AZCAPOTZALCO_ID);
		listaEstacionNumero.add(MetroConstant.FERRERIA_ID);
		listaEstacionNumero.add(MetroConstant.NORTE_45_ID);
		listaEstacionNumero.add(MetroConstant.VALLEJO_ID);
		listaEstacionNumero.add(MetroConstant.INSTITUTO_DEL_PETROLEO_ID);
		listaEstacionNumero.add(MetroConstant.LINDAVISTA_ID);
		listaEstacionNumero.add(MetroConstant.DEPORTIVO_18_DE_MARZO_ID);
		listaEstacionNumero.add(MetroConstant.LA_VILLA_BASILICA_ID);
		listaEstacionNumero.add(MetroConstant.MARTIN_CARRERA_ID);
		this.linea6.setListaEstacionNumero(listaEstacionNumero);
		return this.linea6;
	}

	public MetroJbLinea getLinea7() {
		this.linea7 = new MetroJbLinea();
		this.linea7.setColor("naranja");
		this.linea7.setNombre("Linea 7");
		this.linea7.setColorHex("#E05F0F");
		this.linea7.setId("7");
		this.linea7.setImage(R.drawable.linea7);
		this.linea7.setLongitud(17.011);
		this.linea7.setInicioTag(MetroConstant.HORIZONTAL_ARRIBA);
		this.linea7.setFinalTag(MetroConstant.HORIZONTAL_ABAJO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.EL_ROSARIO_ID);
		listaEstacionNumero.add(MetroConstant.AQUILES_SEDAN_ID);
		listaEstacionNumero.add(MetroConstant.CAMARONES_ID);
		listaEstacionNumero.add(MetroConstant.REFINERIA_ID);
		listaEstacionNumero.add(MetroConstant.TACUBA_ID);
		listaEstacionNumero.add(MetroConstant.SAN_JOAQUIN_ID);
		listaEstacionNumero.add(MetroConstant.POLANCO_ID);
		listaEstacionNumero.add(MetroConstant.AUDITORIO_ID);
		listaEstacionNumero.add(MetroConstant.CONSTITUYENTES_ID);
		listaEstacionNumero.add(MetroConstant.TACUBAYA_ID);
		listaEstacionNumero.add(MetroConstant.SAN_PEDRO_DE_LOS_PINOS_ID);
		listaEstacionNumero.add(MetroConstant.SAN_ANTONIO_ID);
		listaEstacionNumero.add(MetroConstant.MIXCOAC_ID);
		listaEstacionNumero.add(MetroConstant.BARRANCA_DEL_MUERTO_ID);
		this.linea7.setListaEstacionNumero(listaEstacionNumero);
		return this.linea7;
	}

	public MetroJbLinea getLinea8() {
		this.linea8 = new MetroJbLinea();
		this.linea8.setColor("verde");
		this.linea8.setNombre("Linea 8");
		this.linea8.setColorHex("#1A9C6D");
		this.linea8.setId("8");
		this.linea8.setLongitud(17.679);
		this.linea8.setImage(R.drawable.linea8);		
		this.linea8.setInicioTag(MetroConstant.HORIZONTAL_ARRIBA);
		this.linea8.setFinalTag(MetroConstant.VERTICAL_DERECHO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.GARIBALDI_ID);
		listaEstacionNumero.add(MetroConstant.BELLAS_ARTES_ID);
		listaEstacionNumero.add(MetroConstant.SAN_JUAN_DE_LETRAN_ID);
		listaEstacionNumero.add(MetroConstant.SALTO_DEL_AGUA_ID);
		listaEstacionNumero.add(MetroConstant.DOCTORES_ID);
		listaEstacionNumero.add(MetroConstant.OBRERA_ID);
		listaEstacionNumero.add(MetroConstant.CHABACANO_ID);
		listaEstacionNumero.add(MetroConstant.LA_VIGA_ID);
		listaEstacionNumero.add(MetroConstant.SANTA_ANITA_ID);
		listaEstacionNumero.add(MetroConstant.COYUYA_ID);
		listaEstacionNumero.add(MetroConstant.IZTACALCO_ID);
		listaEstacionNumero.add(MetroConstant.APATLACO_ID);
		listaEstacionNumero.add(MetroConstant.ACULCO_ID);
		listaEstacionNumero.add(MetroConstant.ESCUADRON_201_ID);
		listaEstacionNumero.add(MetroConstant.ATLALILCO_ID);
		listaEstacionNumero.add(MetroConstant.IZTAPALAPA_ID);
		listaEstacionNumero.add(MetroConstant.CERRO_DE_LA_ESTRELLA_ID);
		listaEstacionNumero.add(MetroConstant.UAM_I_ID);
		listaEstacionNumero.add(MetroConstant.CONSTITUCION_DE_1917_ID);
		this.linea8.setListaEstacionNumero(listaEstacionNumero);
		return this.linea8;
	}

	public MetroJbLinea getLinea9() {
		this.linea9 = new MetroJbLinea();
		this.linea9.setColor("cafe");
		this.linea9.setNombre("Linea 9");
		this.linea9.setId("9");
		this.linea9.setColorHex("#5C2B2A");
		this.linea9.setLongitud(13.033);
		this.linea9.setImage(R.drawable.linea9);	
		this.linea9.setInicioTag(MetroConstant.HORIZONTAL_ABAJO);
		this.linea9.setFinalTag(MetroConstant.HORIZONTAL_ABAJO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.TACUBAYA_ID);
		listaEstacionNumero.add(MetroConstant.PATRIOTISMO_ID);
		listaEstacionNumero.add(MetroConstant.CHILPANCINGO_ID);
		listaEstacionNumero.add(MetroConstant.CENTRO_MEDICO_ID);
		listaEstacionNumero.add(MetroConstant.LAZARO_CARDENAS_ID);
		listaEstacionNumero.add(MetroConstant.CHABACANO_ID);
		listaEstacionNumero.add(MetroConstant.JAMAICA_ID);
		listaEstacionNumero.add(MetroConstant.MIXIUHCA_ID);
		listaEstacionNumero.add(MetroConstant.VELODROMO_ID);
		listaEstacionNumero.add(MetroConstant.CIUDAD_DEPORTIVA_ID);
		listaEstacionNumero.add(MetroConstant.PUEBLA_ID);
		listaEstacionNumero.add(MetroConstant.PANTITLAN_ID);
		this.linea9.setListaEstacionNumero(listaEstacionNumero);
		return this.linea9;
	}

	public MetroJbLinea getLineaA() {
		this.lineaA = new MetroJbLinea();
		this.lineaA.setColor("morado");
		this.lineaA.setNombre("Linea A");
		this.lineaA.setColorHex("#A02E88");
		this.lineaA.setId("a");
		this.lineaA.setImage(R.drawable.lineaa);
		this.lineaA.setLongitud(14.893);		
		this.lineaA.setInicioTag(MetroConstant.VERTICAL_DERECHO);
		this.lineaA.setFinalTag(MetroConstant.HORIZONTAL_ABAJO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.PANTITLAN_ID);
		listaEstacionNumero.add(MetroConstant.AGRICOLA_ORIENTAL_ID);
		listaEstacionNumero.add(MetroConstant.CANAL_DE_SAN_JUAN_ID);
		listaEstacionNumero.add(MetroConstant.TEPALCATES_ID);
		listaEstacionNumero.add(MetroConstant.GUELATAO_ID);
		listaEstacionNumero.add(MetroConstant.PENON_VIEJO_ID);
		listaEstacionNumero.add(MetroConstant.ACATITLA_ID);
		listaEstacionNumero.add(MetroConstant.SANTA_MARTA_ID);
		listaEstacionNumero.add(MetroConstant.LOS_REYES_ID);
		listaEstacionNumero.add(MetroConstant.LA_PAZ_ID);
		this.lineaA.setListaEstacionNumero(listaEstacionNumero);
		return this.lineaA;
	}

	public MetroJbLinea getLineaB() {
		this.lineaB = new MetroJbLinea();
		this.lineaB.setColor("verde gris");
		this.lineaB.setNombre("Linea B");
		this.lineaB.setColorHex("#0A8E67");
		this.lineaB.setColorHex2("#B5B5AF");
		this.lineaB.setId("b");
		this.lineaB.setImage(R.drawable.lineab);
		this.lineaB.setLongitud(20.278);	
		this.lineaB.setInicioTag(MetroConstant.HORIZONTAL_ARRIBA);
		this.lineaB.setFinalTag(MetroConstant.VERTICAL_IZQUIERDO);
		ArrayList<Integer> listaEstacionNumero = new ArrayList<Integer>();
		listaEstacionNumero.add(MetroConstant.CIUDAD_AZTECA_ID);
		listaEstacionNumero.add(MetroConstant.PLAZA_ARAGON_ID);
		listaEstacionNumero.add(MetroConstant.OLIMPICA_ID);
		listaEstacionNumero.add(MetroConstant.ECATEPEC_ID);
		listaEstacionNumero.add(MetroConstant.MUZQUIZ_ID);
		listaEstacionNumero.add(MetroConstant.RIO_DE_LOS_REMEDIOS_ID);
		listaEstacionNumero.add(MetroConstant.IMPULSORA_ID);
		listaEstacionNumero.add(MetroConstant.NEZAHUALCOYOTL_ID);
		listaEstacionNumero.add(MetroConstant.VILLA_DE_ARAGON_ID);
		listaEstacionNumero.add(MetroConstant.BOSQUE_DE_ARAGON_ID);
		listaEstacionNumero.add(MetroConstant.DEPORTIVO_OCEANIA_ID);
		listaEstacionNumero.add(MetroConstant.OCEANIA_ID);
		listaEstacionNumero.add(MetroConstant.ROMERO_RUBIO_ID);
		listaEstacionNumero.add(MetroConstant.R_FLORES_MAGON_ID);
		listaEstacionNumero.add(MetroConstant.SAN_LAZARO_ID);
		listaEstacionNumero.add(MetroConstant.MORELOS_ID);
		listaEstacionNumero.add(MetroConstant.TEPITO_ID);
		listaEstacionNumero.add(MetroConstant.LAGUNILLA_ID);
		listaEstacionNumero.add(MetroConstant.GARIBALDI_ID);
		listaEstacionNumero.add(MetroConstant.GUERRERO_ID);
		listaEstacionNumero.add(MetroConstant.BUENAVISTA_ID);
		this.lineaB.setListaEstacionNumero(listaEstacionNumero);
		return this.lineaB;
	}

	public ArrayList<MetroJbLinea> getListaLinea() {
		return listaLinea;
	}

	public SparseArray<MetroJbEstacion> getMapaEstacion() {
		return mapaEstacion;
	}
	public void setSegmentos(ArrayList<MetroJbSegmento> segmentos) {
		this.segmentos = segmentos;
	}
	public ArrayList<MetroJbSegmento> getSegmentos() {
		return this.segmentos;
	}

	public void setListaLinea(ArrayList<MetroJbLinea> listaLinea) {
		this.listaLinea = listaLinea;
	}

	public void setMapaEstacion(SparseArray<MetroJbEstacion> mapaEstacion) {
		this.mapaEstacion = mapaEstacion;
	}
}
