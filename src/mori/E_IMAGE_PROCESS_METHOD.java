package mori;

enum E_IMAGE_PROCESS_METHOD{

	//! UNDO
	E_UNDO,

	//! 簡便な画像縮小
	E_CONTRACT_AVERAGE,

	//! 乱数を用いた画像縮小
	E_CONTRACT_RANDOM,

	//! 背景を塗る
	E_NURU,

	//! ヒストグラム等価処理
	E_EQUIVALENCE_HISTOGRAM,

	//! 輝度を用いたエッジ抽出
	E_EXTRACT_EDGE_LUMI,

	//! RGBの差によるエッジ抽出
	E_EXTRACT_EDGE_RGB,

	//! 減色
	E_SUBTRACT_COLOR,

	//! エンボス
	E_EMBOSS,

	//! RGB SWAP
	E_SWAP_RGB,

	//! 補色
	E_COMPLEMENTRAY_COLOR,

	//! FOG
	E_FOG,

	E_CATEGORY,

	E_DOMINO,

	E_DOMINO_RGB,

	E_CLOUD_EXTRACTION,

	E_CLOUD_ERASURE,

	E_RAIN_EXTRACTION,

	E_RAIN_ERASURE,

	E_90_DEGREES_CLOCKWISE,

	E_GASKET,

	E_GASKET2,

	E_KOCH,

	E_SSANGYONG,

	E_FERN,

	E_FRACTAL2,

	E_FRACTAL3,

	E_POLYGON_CONNECT,

	E_GRAY_SCALSE,

	E_VORONOI,

	E_VORONOI_2,
	
	E_KURATOWSKI,
	
	E_SYMMETRY,
	
	E_ELLIPSE;
}

