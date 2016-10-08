
public class NeuralNetwork2 {
	/***
	 * 類神經2 3 2 2
	 * 2x + 3y = z
	 * 
	 * @param args
	 */
	public static final int tranX = 2, tranY = 800;
	public static final int testX = 2, testY = 200;
	public double[][] tranData = new double[tranX][tranY]; // 訓練資料
	public double[][] testData = new double[testX][testY]; // 測試資料
	public double maxTranX, minTranX, maxTranY, minTranY;
	public double maxTestX, minTestX, maxTestY, minTestY;
	public double[] expectValue, actualValue, errorValue;
	public double[] functionQ, outputQ;// TODO 要知道這個是不是設對了
	double weight1, weight2, d1, learnRate = 0.001;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NeuralNetwork2 neuralNetwork = new NeuralNetwork2();

		neuralNetwork.assignTranMatrix(neuralNetwork);
		System.out.println();
		neuralNetwork.assignTestMatrix(neuralNetwork);
		System.out.println();

		System.out.println("訓練最大值: " + neuralNetwork.maxTranX + " 訓練最小值: " + neuralNetwork.minTranX);
		System.out.println("測試最大值: " + neuralNetwork.maxTestX + " 測試最小值: " + neuralNetwork.minTestX);
		System.out.println();

		normalization(neuralNetwork);
		System.out.println();
		trainingExceptValue(neuralNetwork);
	}

	// 分配訓練資料
	public static void assignTranMatrix(NeuralNetwork2 neuralNetwork) {
		int x = 0, y = 0;
		System.out.println("初始化訓練資料");
		neuralNetwork.maxTranX = neuralNetwork.minTranX = neuralNetwork.tranData[0][0] = (Math.random() * 100);
		neuralNetwork.maxTranY = neuralNetwork.minTranY = neuralNetwork.tranData[1][0] = (Math.random() * 100);
		y = 1;
		System.out.print(neuralNetwork.tranData[0][0] + "  ");
		for (int i = 0 + x; i < tranX; i++) {
			for (int j = 0 + y; j < tranY; j++) {
				neuralNetwork.tranData[i][j] = (Math.random() * 100);
				System.out.print(neuralNetwork.tranData[i][j] + " ");
				if (i == 0) {
					if (neuralNetwork.maxTranX < neuralNetwork.tranData[i][j])
						neuralNetwork.maxTranX = neuralNetwork.tranData[i][j];
					if (neuralNetwork.minTranX > neuralNetwork.tranData[i][j])
						neuralNetwork.minTranX = neuralNetwork.tranData[i][j];
				} else {
					if (neuralNetwork.maxTranY < neuralNetwork.tranData[i][j])
						neuralNetwork.maxTranY = neuralNetwork.tranData[i][j];
					if (neuralNetwork.minTranY > neuralNetwork.tranData[i][j])
						neuralNetwork.minTranY = neuralNetwork.tranData[i][j];
				}
			}
			System.out.print(neuralNetwork.tranData[x][0] + "  ");
			x = 1;
			System.out.println();
		}
	}

	// 分配測試資料
	public static void assignTestMatrix(NeuralNetwork2 neuralNetwork) {
		int x = 0, y = 0;
		System.out.println("初始化測試資料");
		neuralNetwork.maxTestX = neuralNetwork.minTestX = neuralNetwork.testData[0][0] = (Math.random() * 100);
		neuralNetwork.maxTestY = neuralNetwork.minTestY = neuralNetwork.testData[1][0] = (Math.random() * 100);
		y = 1;
		System.out.print(neuralNetwork.testData[0][0] + "  ");
		for (int i = 0 + x; i < testX; i++) {
			for (int j = 0 + y; j < testY; j++) {
				neuralNetwork.testData[i][j] = (Math.random() * 100);
				System.out.print(neuralNetwork.testData[i][j] + " ");
				if (i == 0) {
					if (neuralNetwork.maxTestX < neuralNetwork.testData[i][j])
						neuralNetwork.maxTestX = neuralNetwork.testData[i][j];
					if (neuralNetwork.minTestX > neuralNetwork.testData[i][j])
						neuralNetwork.minTestX = neuralNetwork.testData[i][j];
				} else {
					if (neuralNetwork.maxTestY < neuralNetwork.testData[i][j])
						neuralNetwork.maxTestY = neuralNetwork.testData[i][j];
					if (neuralNetwork.minTestY > neuralNetwork.testData[i][j])
						neuralNetwork.minTestY = neuralNetwork.testData[i][j];
				}
			}
			x = 1;
			System.out.println();
		}
	}

	// 正規化
	public static void normalization(NeuralNetwork2 neuralNetwork) {
		// 訓練資料
		System.out.println("正規化訓練資料");
		for (int i = 0; i < neuralNetwork.tranX; i++) {
			for (int j = 0; j < neuralNetwork.tranY; j++) {
				if (i == 0) {
					neuralNetwork.tranData[i][j] = ((neuralNetwork.tranData[i][j] - neuralNetwork.minTranX)
							/ (neuralNetwork.maxTranX - neuralNetwork.minTranX)) * 2 - 1;
				} else {
					neuralNetwork.tranData[i][j] = ((neuralNetwork.tranData[i][j] - neuralNetwork.minTranY)
							/ (neuralNetwork.maxTranY - neuralNetwork.minTranY)) * 2 - 1;
				}
				System.out.print(neuralNetwork.tranData[i][j] + "  ");
			}
			System.out.println();
		}

		// 測試資料
		System.out.println();
		System.out.println("正規化測試資料");
		for (int i = 0; i < neuralNetwork.testX; i++) {
			for (int j = 0; j < neuralNetwork.testY; j++) {
				if (i == 0) {
					neuralNetwork.testData[i][j] = ((neuralNetwork.testData[i][j] - neuralNetwork.minTestX)
							/ (neuralNetwork.maxTestX - neuralNetwork.minTestX)) * 2 - 1;
				} else {
					neuralNetwork.testData[i][j] = ((neuralNetwork.testData[i][j] - neuralNetwork.minTestY)
							/ (neuralNetwork.maxTestY - neuralNetwork.minTestY)) * 2 - 1;
				}
				System.out.print(neuralNetwork.testData[i][j] + "  ");
			}
			System.out.println();
		}
	}

	// 取得期望值
	public static void trainingExceptValue(NeuralNetwork2 neuralNetwork) {
		neuralNetwork.expectValue = new double[tranY];
		System.out.println("期望值");
		for (int i = 0; i < neuralNetwork.tranY; i++) {
			neuralNetwork.expectValue[i] = 2 * neuralNetwork.tranData[0][i] + 3 * neuralNetwork.tranData[1][i];
			System.out.print(neuralNetwork.expectValue[i] + "  ");
		}
	}

	public static void layer2(NeuralNetwork2 neuralNetwork) {
		int a, b;
		for (int i = 0; i < neuralNetwork.tranX; i++) {
			for (int j = 0; j < neuralNetwork.tranY; j++) {

			}
		}
	}

	public static void layer3(NeuralNetwork2 neuralNetwork) {

	}

	public static void backwardW2(NeuralNetwork2 neuralNetwork) {

	}
	
	public static void backwardD1(NeuralNetwork2 neuralNetwork) {

	}
	
	public static void backwardW1(NeuralNetwork2 neuralNetwork) {

	}
}
