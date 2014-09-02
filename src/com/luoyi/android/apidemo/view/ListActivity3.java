package com.luoyi.android.apidemo.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.luoyi.android.apidemo.BaseActivity;
import com.luoyi.android.apidemo.R;

public class ListActivity3 extends BaseActivity {

	private ListView mListView;
	/*
	 * 前个字符
	 */
	private char mPerChar;

	/*
	 * 是否显示
	 */
	private boolean mIsShowing;

	/*
	 * 提示框
	 */
	private TextView mDialogText;

	private Handler mHandler = new Handler();

	private WindowManager mWindowManager;

	private HiddenDialog mHiddenDialog;

	private boolean mReady;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_1);
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		mDialogText = new TextView(getApplicationContext());
		mDialogText.setVisibility(View.INVISIBLE);
		mHiddenDialog = new HiddenDialog();
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				mReady = true;
				WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT,
						WindowManager.LayoutParams.TYPE_APPLICATION,
						WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
								| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
						PixelFormat.TRANSLUCENT);
				mWindowManager.addView(mDialogText, lp);
			}
		});
		mListView = (ListView) findViewById(R.id.list_1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.list_text_item, mStrings);
		mListView.setAdapter(adapter);
		mListView.setOnScrollListener(new MyOnScrollListener());

	}

	private class MyOnScrollListener implements OnScrollListener {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// System.out.println("scrollState" + scrollState);
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if (mReady) {
				char firstChar = mStrings[firstVisibleItem].charAt(0);
				if (mPerChar != firstChar) {
					mPerChar = firstChar;
					showCharDialog(firstChar);
				}
			}
		}
	}

	private void showCharDialog(char firstChar) {
		if (!mIsShowing) {
			mDialogText.setVisibility(View.VISIBLE);
			mIsShowing = true;
		}
		mDialogText.setText(String.valueOf(firstChar));
		mHandler.removeCallbacks(mHiddenDialog);
		mHandler.postDelayed(mHiddenDialog, 3000);
	}

	private final class HiddenDialog implements Runnable {
		public void run() {
			hiddenCharDialog();
		}
	}

	private void hiddenCharDialog() {
		if (mIsShowing) {
			mIsShowing = false;
			mDialogText.setVisibility(View.INVISIBLE);
		}
	}

	private String[] mStrings = { "Abbaye de Belloc",
			"Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
			"Alverca", "Ambert", "American Cheese", "Ami du Chambertin",
			"Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
			"Aragon", "Ardi Gasna", "Ardrahan", "Armenian String",
			"Aromes au Gene de Marc", "Asadero", "Asiago", "Aubisque Pyrenees",
			"Autun", "Avaxtskyr", "Baby Swiss", "Babybel",
			"Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal",
			"Banon", "Barry's Bay Cheddar", "Basing", "Basket Cheese",
			"Bath Cheese", "Bavarian Bergkase", "Baylough", "Beaufort",
			"Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
			"Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir",
			"Bierkase", "Bishop Kennedy", "Blarney", "Bleu d'Auvergne",
			"Bleu de Gex", "Bleu de Laqueuille", "Bleu de Septmoncel",
			"Buchette d'Anjou", "Buffalo", "Burgos", "Butte", "Butterkase",
			"Button (Innes)", "Buxton Blue", "Cabecou", "Caboc", "Cabrales",
			"Cachaille", "Caciocavallo", "Caciotta", "Caerphilly",
			"Cairnsmore", "Calenzana", "Cambazola", "Camembert de Normandie",
			"Canadian Cheddar", "Canestrato", "Cantal", "Caprice des Dieux",
			"Capricorn Goat", "Capriole Banon", "Carre de l'Est",
			"Casciotta di Urbino", "Cashel Blue", "Castellano", "Castelleno",
			"Castelmagno", "Castelo Branco", "Castigliano", "Cathelain",
			"Celtic Promise", "Cendre d'Olivet", "Cerney", "Chabichou",
			"Cypress Grove Chevre", "Danablu (Danish Blue)", "Danbo",
			"Danish Fontina", "Daralagjazsky", "Dauphin", "Delice des Fiouves",
			"Denhany Dorset Drum", "Derby", "Dessertnyj Belyj", "Devon Blue",
			"Devon Garland", "Dolcelatte", "Doolin", "Doppelrhamstufel",
			"Dorset Blue Vinney", "Double Gloucester", "Double Worcester",
			"Dreux a la Feuille", "Dry Jack", "Duddleswell", "Dunbarra",
			"Dunlop", "Dunsyre Blue", "Duroblando", "Durrus",
			"Dutch Mimolette (Commissiekaas)", "Edam", "Edelpilz",
			"Emental Grand Cru", "Emlett", "Emmental", "Epoisses de Bourgogne",
			"Esbareich", "Esrom", "Etorki", "Evansdale Farmhouse Brie",
			"Evora De L'Alentejo", "Exmoor Blue", "Explorateur", "Feta",
			"Feta (Australian)", "Figue", "Filetta", "Fin-de-Siecle",
			"Four Herb Gouda", "Fourme d' Ambert", "Fourme de Haute Loire",
			"Fourme de Montbrison", "Fresh Jack", "Fresh Mozzarella",
			"Fresh Ricotta", "Fresh Truffles", "Fribourgeois", "Friesekaas",
			"Friesian", "Friesla", "Frinault", "Fromage a Raclette",
			"Fromage Corse", "Fromage de Montagne de Savoie", "Fromage Frais",
			"Fruit Cream Cheese", "Frying Cheese", "Fynbo", "Gabriel",
			"Galette du Paludier", "Galette Lyonnaise",
			"Galloway Goat's Milk Gems", "Gammelost", "Gaperon a l'Ail",
			"Garrotxa", "Gastanberra", "Geitost", "Gippsland Blue", "Gjetost",
			"Gloucester", "Golden Cross", "Gorgonzola", "Gornyaltajski",
			"Gospel Green", "Gouda", "Goutu", "Gowrie", "Grabetto", "Graddost",
			"Grafton Village Cheddar", "Grana", "Grana Padano", "Grand Vatel",
			"Grataron d' Areches", "Gratte-Paille", "Graviera", "Greuilh",
			"Greve", "Gris de Lille", "Gruyere", "Gubbeen", "Guerbigny",
			"Halloumi", "Halloumy (Australian)", "Haloumi-Style Cheese",
			"Harbourne Blue", "Havarti", "Heidi Gruyere", "Hereford Hop",
			"Herrgardsost", "Herriot Farmhouse", "Herve", "Hipi Iti",
			"Hubbardston Blue Cow", "Hushallsost", "Iberico", "Idaho Goatster",
			"Idiazabal", "Il Boschetto al Tartufo", "Ile d'Yeu",
			"Isle of Mull", "Jarlsberg", "Jermi Tortes", "Jibneh Arabieh",
			"Jindi Brie", "Jubilee Blue", "Juustoleipa", "Kadchgall", "Kaseri",
			"Kashta", "Kefalotyri", "Kenafa", "Kernhem", "Kervella Affine",
			"Kikorangi", "King Island Cape Wickham Brie", "King River Gold",
			"Klosterkaese", "Knockalara", "Kugelkase", "L'Aveyronnais",
			"L'Ecir de l'Aubrac", "La Taupiniere", "La Vache Qui Rit",
			"Laguiole", "Lairobell", "Lajta", "Lanark Blue", "Lancashire",
			"Langres", "Lappi", "Laruns", "Lavistown", "Le Brin",
			"Le Fium Orbo", "Le Lacandou", "Le Roule", "Leafield", "Lebbene",
			"Leerdammer", "Leicester", "Leyden", "Limburger",
			"Lincolnshire Poacher", "Lingot Saint Bousquet d'Orb", "Liptauer",
			"Little Rydings", "Livarot", "Llanboidy", "Llanglofan Farmhouse",
			"Loch Arthur Farmhouse", "Loddiswell Avondale", "Longhorn",
			"Lou Palou", "Lou Pevre", "Lyonnais", "Maasdam", "Macconais",
			"Mahoe Aged Gouda", "Mahon", "Malvern", "Mamirolle", "Manchego",
			"Manouri", "Manur", "Marble Cheddar", "Marbled Cheeses", "Murol",
			"Mycella", "Myzithra", "Naboulsi", "Nantais", "Neufchatel",
			"Neufchatel (Australian)", "Niolo", "Nokkelost", "Northumberland",
			"Oaxaca", "Olde York", "Olivet au Foin", "Olivet Bleu",
			"Olivet Cendre", "Orkney Extra Mature Cheddar", "Orla",
			"Oschtjepka", "Ossau Fermier", "Ossau-Iraty", "Oszczypek",
			"Oxford Blue", "P'tit Berrichon", "Palet de Babligny", "Paneer",
			"Panela", "Pannerone", "Pant ys Gawn", "Parmesan (Parmigiano)",
			"Parmigiano Reggiano", "Pas de l'Escalette", "Passendale",
			"Pasteurized Processed", "Pate de Fromage", "Patefine Fort",
			"Pave d'Affinois", "Pave d'Auge", "Pave de Chirac",
			"Pelardon des Corbieres", "Penamellera", "Penbryn", "Pencarreg",
			"Perail de Brebis", "Petit Morin", "Petit Pardou", "Petit-Suisse",
			"Picodon de Chevre", "Picos de Europa", "Piora",
			"Pithtviers au Foin", "Plateau de Herve", "Plymouth Cheese",
			"Podhalanski", "Poivre d'Ane", "Polkolbin", "Pont l'Eveque",
			"Port Nicholson", "Port-Salut", "Postel", "Pouligny-Saint-Pierre",
			"Pourly", "Prastost", "Pressato", "Prince-Jean",
			"Processed Cheddar", "Provolone", "Provolone (Australian)",
			"Pyengana Cheddar", "Pyramide", "Quark", "Quark (Australian)",
			"Quartirolo Lombardo", "Quatre-Vents", "Quercy Petit",
			"Queso Blanco", "Queso Blanco con Frutas --Pina y Mango",
			"Queso Para Frier", "Queso Quesadilla", "Rabacal", "Raclette",
			"Ragusano", "Raschera", "Reblochon", "Red Leicester",
			"Regal de la Dombes", "Reggianito", "Remedou", "Requeson",
			"Richelieu", "Ricotta", "Ricotta (Australian)", "Ricotta Salata",
			"Ridder", "Rigotte", "Rocamadour", "Rollot", "Romano",
			"Romans Part Dieu", "Roncal", "Roquefort", "Roule",
			"Rouleau De Beaulieu", "Royalp Tilsit", "Rubens", "Rustinu",
			"Sottocenare al Tartufo", "Soumaintrain", "Sourire Lozerien",
			"Spenwood", "Sraffordshire Organic", "St. Agur Blue Cheese",
			"Stilton", "Stinking Bishop", "String", "Sussex Slipcote",
			"Sveciaost", "Swaledale", "Sweet Style Swiss", "Swiss",
			"Syrian (Armenian String)", "Tala", "Taleggio", "Tamie",
			"Tasmania Highland Chevre Log", "Taupiniere", "Teifi", "Telemea",
			"Testouri", "Tete de Moine", "Tetilla", "Texas Goat Cheese",
			"Tibet", "Tillamook Cheddar", "Tilsit", "Timboon Brie", "Toma",
			"Tomme Brulee", "Tomme d'Abondance", "Tomme de Chevre", "Tronchon",
			"Trou du Cru", "Truffe", "Tupi", "Turunmaa", "Tymsboro",
			"Tyn Grug", "Tyning", "Ubriaco", "Ulloa", "Vacherin-Fribourgeois",
			"Valencay", "Vasterbottenost", "Venaco", "Vendomois",
			"Vieux Corse", "Vignotte", "Vulscombe", "Waimata Farmhouse Blue",
			"Washed Rind Cheese (Australian)", "Woodside Cabecou", "Xanadu",
			"Xynotyro", "Yarg Cornish", "Yarra Valley Pyramid",
			"Yorkshire Blue", "Zamorano", "Zanetti Grana Padano",
			"Zanetti Parmigiano Reggiano" };
}
