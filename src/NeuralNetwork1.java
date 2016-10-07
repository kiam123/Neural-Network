
public class NeuralNetwork1 {
	/***
	 * 類神經1 3 1 
	 * 2x+3y = z
	 * 
	 * @param args
	 */
	private static final int tranX = 2, tranY = 800;
	private static final int testX = 2, testY = 200;
	public double[][] tranData = new double[tranX][tranY]; //訓練資料
	public double[][] testData = new double[testX][testY]; //測試資料
	public double maxTranX, minTranX, maxTranY, minTranY, maxTestX, minTestX, maxTestY, minTestY;
	public double []expectValue, actualValue;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NeuralNetwork1 neuralNetwork = new NeuralNetwork1();
		
		neuralNetwork.assignTranMatrix(neuralNetwork);
		System.out.println();
		neuralNetwork.assignTestMatrix(neuralNetwork);
		System.out.println();
		
		System.out.println("maxTranValue: "+neuralNetwork.maxTranX+" minTranValue: "+neuralNetwork.minTranX);
		System.out.println("maxTestValue: "+neuralNetwork.maxTestX+" minTestValue: "+neuralNetwork.minTestX);
		System.out.println();
		
		normalization(neuralNetwork);
	}

	// 分配訓練資料
	public static void assignTranMatrix(NeuralNetwork1 neuralNetwork) {
		int x = 0,y = 0;
		neuralNetwork.maxTranX = neuralNetwork.minTranX = neuralNetwork.tranData[0][0] = (Math.random() * 100);
		neuralNetwork.maxTranY = neuralNetwork.minTranY = neuralNetwork.tranData[1][0] = (Math.random() * 100);
		y = 1;
		System.out.print(neuralNetwork.tranData[0][0]+"  ");
		for (int i = 0 + x; i < tranX; i++) {
			for (int j = 0 + y; j < tranY; j++) {
				neuralNetwork.tranData[i][j] = (Math.random() * 100);
				System.out.print(neuralNetwork.tranData[i][j]+" ");
				if(i == 0){
					if(neuralNetwork.maxTranX < neuralNetwork.tranData[i][j])
						neuralNetwork.maxTranX = neuralNetwork.tranData[i][j];
					if(neuralNetwork.minTranX > neuralNetwork.tranData[i][j])
						neuralNetwork.minTranX = neuralNetwork.tranData[i][j];
				}else{
					if(neuralNetwork.maxTranY < neuralNetwork.tranData[i][j])
						neuralNetwork.maxTranY = neuralNetwork.tranData[i][j];
					if(neuralNetwork.minTranY > neuralNetwork.tranData[i][j])
						neuralNetwork.minTranY = neuralNetwork.tranData[i][j];
				}
			}
			System.out.print(neuralNetwork.tranData[x][0]+"  ");
			x = 1;
			System.out.println();
		}
	}

	// 分配測試資料
	public static void assignTestMatrix(NeuralNetwork1 neuralNetwork) {
		int x = 0,y = 0;
		neuralNetwork.maxTestX = neuralNetwork.minTestX = neuralNetwork.testData[0][0] = (Math.random() * 100);
		neuralNetwork.maxTestY = neuralNetwork.minTestY = neuralNetwork.testData[1][0] = (Math.random() * 100);
		y = 1;
		System.out.print(neuralNetwork.testData[0][0]+"  ");
		for (int i = 0 + x; i < testX; i++) {
			for (int j = 0 + y; j < testY; j++) {
				neuralNetwork.testData[i][j] = (Math.random() * 100);
				System.out.print(neuralNetwork.testData[i][j]+" ");
				if(i == 0){
					if(neuralNetwork.maxTestX < neuralNetwork.testData[i][j])
						neuralNetwork.maxTestX = neuralNetwork.testData[i][j];
					if(neuralNetwork.minTestX > neuralNetwork.testData[i][j])
						neuralNetwork.minTestX = neuralNetwork.testData[i][j];
				}else{
					if(neuralNetwork.maxTestY < neuralNetwork.testData[i][j])
						neuralNetwork.maxTestY = neuralNetwork.testData[i][j];
					if(neuralNetwork.minTestY > neuralNetwork.testData[i][j])
						neuralNetwork.minTestY = neuralNetwork.testData[i][j];
				}
			}
			x = 1;
			System.out.println();
		}
	}
	
	//正規化
	public static void normalization(NeuralNetwork1 neuralNetwork) {
		//訓練資料
		System.out.println("正規化訓練資料");
		for(int i=0;i < neuralNetwork.tranX;i++){
			for(int j=0;j < neuralNetwork.tranY;j++){
				if(i == 0){
					neuralNetwork.tranData[i][j] = ((neuralNetwork.tranData[i][j] - neuralNetwork.minTranX) /
													(neuralNetwork.maxTranX - neuralNetwork.minTranX)) *
													2 - 1;
				}else{
					neuralNetwork.tranData[i][j] = ((neuralNetwork.tranData[i][j] - neuralNetwork.minTranY) /
							(neuralNetwork.maxTranY - neuralNetwork.minTranY)) *
							2 - 1;
				}
				System.out.print(neuralNetwork.tranData[i][j]+"  ");
			}
			System.out.println();
		}
		
		//測試資料
		System.out.println();
		System.out.println("正規化測試資料");
		for(int i=0;i < neuralNetwork.testX;i++){
			for(int j=0;j < neuralNetwork.testY;j++){
				if(i == 0){
					neuralNetwork.testData[i][j] = ((neuralNetwork.testData[i][j] - neuralNetwork.minTestX) /
													(neuralNetwork.maxTestX - neuralNetwork.minTestX)) *
													2 - 1;
				}else{
					neuralNetwork.testData[i][j] = ((neuralNetwork.testData[i][j] - neuralNetwork.minTestY) /
							(neuralNetwork.maxTestY - neuralNetwork.minTestY)) *
							2 - 1;
				}
				System.out.print(neuralNetwork.testData[i][j]+"  ");
			}
			System.out.println();
		}
	}
	
	//取得期望值
	public static void trainingExceptValue(NeuralNetwork1 neuralNetwork){
		for(int i = 0;i < neuralNetwork.maxTranY;i++){
			neuralNetwork.expectValue[i] = 2 * neuralNetwork.tranData[0][i] + 3 * neuralNetwork.tranData[1][i];
		}
	}
	
	
	
}
