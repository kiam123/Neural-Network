
public class NeuralNetwork1 {
	/***
	 * 類神經1 3 1 2x = z
	 * 
	 * @param args
	 */
	public static final int tranX = 800;
	public static final int testX = 200;
	public double[] tranData = new double[tranX]; // 訓練資料
	public double[] testData = new double[testX]; // 測試資料
	public double maxTranX, minTranX;
	public double maxTestX, minTestX;
	public double[] expectValue, actualValue, errorValue;
	public double[] functionQ, outputQ;// TODO 要知道這個是不是設對了
	double weight1, weight2, d1, learnRate = 0.001;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NeuralNetwork1 neuralNetwork = new NeuralNetwork1();

		neuralNetwork.assignTranMatrix(neuralNetwork);
		neuralNetwork.assignTestMatrix(neuralNetwork);

		System.out.println("訓練最大值: " + neuralNetwork.maxTranX + " 訓練最小值: " + neuralNetwork.minTranX);
		System.out.println("測試最大值: " + neuralNetwork.maxTestX + " 測試最小值: " + neuralNetwork.minTestX);
		System.out.println();

		normalization(neuralNetwork);
		trainingExceptValue(neuralNetwork);
	}

	// 分配訓練資料
	public static void assignTranMatrix(NeuralNetwork1 neuralNetwork) {
		System.out.println("初始化訓練資料");
		neuralNetwork.maxTranX = neuralNetwork.minTranX = neuralNetwork.tranData[0] = (Math.random() * 100);
		System.out.print(neuralNetwork.tranData[0] + "  ");

		for (int i = 1; i < tranX; i++) {
			neuralNetwork.tranData[i] = (Math.random() * 100);
			System.out.print(neuralNetwork.tranData[i] + " ");

			if (neuralNetwork.maxTranX < neuralNetwork.tranData[i])
				neuralNetwork.maxTranX = neuralNetwork.tranData[i];
			if (neuralNetwork.minTranX > neuralNetwork.tranData[i])
				neuralNetwork.minTranX = neuralNetwork.tranData[i];
		}
		System.out.print("\n\n");
	}

	// 分配測試資料
	public static void assignTestMatrix(NeuralNetwork1 neuralNetwork) {
		System.out.println("初始化測試資料");
		neuralNetwork.maxTestX = neuralNetwork.minTestX = neuralNetwork.testData[0] = (Math.random() * 100);
		System.out.print(neuralNetwork.testData[0] + "  ");

		for (int i = 1; i < testX; i++) {
			neuralNetwork.testData[i] = (Math.random() * 100);
			System.out.print(neuralNetwork.testData[i] + " ");

			if (neuralNetwork.maxTestX < neuralNetwork.testData[i])
				neuralNetwork.maxTestX = neuralNetwork.testData[i];
			if (neuralNetwork.minTestX > neuralNetwork.testData[i])
				neuralNetwork.minTestX = neuralNetwork.testData[i];
		}
		System.out.print("\n\n");
	}

	public static void normalization(NeuralNetwork1 neuralNetwork) {
		System.out.println("正規化訓練資料");
		for (int i = 0; i < tranX; i++) {
			neuralNetwork.tranData[i] = ((neuralNetwork.tranData[i] - neuralNetwork.minTranX)
					/ (neuralNetwork.maxTranX - neuralNetwork.minTranX)) * 2 - 1;
			System.out.print(neuralNetwork.tranData[i] + " ");
		}
		System.out.print("\n\n");

		System.out.println("正規化測試資料");
		for (int i = 0; i < testX; i++) {
			neuralNetwork.testData[i] = ((neuralNetwork.testData[i] - neuralNetwork.minTestX)
					/ (neuralNetwork.maxTestX - neuralNetwork.minTestX)) * 2 - 1;
			System.out.print(neuralNetwork.testData[i] + " ");
		}
		System.out.print("\n\n");
	}

	// 取得期望值
	public static void trainingExceptValue(NeuralNetwork1 neuralNetwork) {
		neuralNetwork.expectValue = new double[tranX];
		System.out.println("期望值");
		for (int i = 0; i < neuralNetwork.tranX; i++) {
			neuralNetwork.expectValue[i] = 2 * neuralNetwork.tranData[i];
			System.out.print(neuralNetwork.expectValue[i] + "  ");
		}
		System.out.print("\n\n");
	}
	
	// 取得實際值
	public static void trainingActualValue(NeuralNetwork1 neuralNetwork){
		
	}
}
