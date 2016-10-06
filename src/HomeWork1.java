
public class HomeWork1 {
	/***
	 * 類神經1 3 1 2x+3y = z
	 * 
	 * @param args
	 */
	private static final int tranX = 2, tranY = 800;
	private static final int testX = 2, testY = 200;
	public double[][] tranData = new double[tranX][tranY];
	public double[][] testData = new double[testX][testY];
	public double maxTranValue, minTranValue, maxTestValue, minTestValue;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HomeWork1 neuralNetwork = new HomeWork1();
		neuralNetwork.assignTranMatrix(neuralNetwork);
		neuralNetwork.assignTestMatrix(neuralNetwork);

		neuralNetwork.normalization(neuralNetwork);
		neuralNetwork.normalization(neuralNetwork);
	}

	// 分配訓練資料
	public static void assignTranMatrix(HomeWork1 neuralNetwork) {
		neuralNetwork.maxTranValue = neuralNetwork.minTranValue = neuralNetwork.tranData[0][0] = (Math.random() * 100);
		for (int i = 0; i < tranX; i++) {
			for (int j = 0; j < tranY; j++) {
				neuralNetwork.tranData[i][j] = (Math.random() * 100);

				// System.out.print(neuralNwtwork.tranData[i][j]+" ");
			}
			System.out.println();
		}
	}

	// 分配測試資料
	public static void assignTestMatrix(HomeWork1 neuralNetwork) {
		neuralNetwork.maxTestValue = neuralNetwork.minTestValue = neuralNetwork.testData[0][0] = (Math.random() * 100);
		for (int i = 0; i < testX; i++) {
			for (int j = 0; j < testY; j++) {
				neuralNetwork.testData[i][j] = (Math.random() * 100);
				// System.out.println(neuralNwtwork.testData[i][j]);
			}
		}
	}

	public static void normalization(HomeWork1 neuralNwtwork) {

	}
}
