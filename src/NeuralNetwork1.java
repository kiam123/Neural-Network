import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NeuralNetwork1 {
	/***
	 * 類神經1 3 1 2x = z
	 * 
	 * @param args
	 */
	public static final int tranX = 800;
	public static final int testX = 200;
	public static final double learnRate = 0.001;
	public double[] tranData = new double[tranX]; // 訓練資料
	public double[] testData = new double[testX]; // 測試資料
	public double maxTranX, minTranX;
	public double maxTestX, minTestX;
	public double[] expectValue, actualValue, errorValue;
	public double[][] funcTranQ, outputTranQ, tranWeight1, tranWeight2;
	public double[][] functionTestQ, outputTestQ, testWeight1, testWeight2, d1;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		NeuralNetwork1 neuralNetwork = new NeuralNetwork1();

		neuralNetwork.initTranMatrix(neuralNetwork);
		neuralNetwork.initTestMatrix(neuralNetwork);
		neuralNetwork.initWeight(neuralNetwork);

		System.out.println("訓練最大值: " + neuralNetwork.maxTranX + " 訓練最小值: " + neuralNetwork.minTranX);
		System.out.println("測試最大值: " + neuralNetwork.maxTestX + " 測試最小值: " + neuralNetwork.minTestX);
		System.out.println();

		normalization(neuralNetwork);
		trainingExceptValue(neuralNetwork);

		layer2(neuralNetwork);
		layer3(neuralNetwork);
		findError(neuralNetwork);
		
		updateMessenger(neuralNetwork);
	}

	// 分配訓練資料
	public static void initTranMatrix(NeuralNetwork1 neuralNetwork) throws IOException {
		System.out.println("初始化訓練資料");
		neuralNetwork.maxTranX = neuralNetwork.minTranX = neuralNetwork.tranData[0] = (Math.random() * 100);
		System.out.print(neuralNetwork.tranData[0] + "  ");
		for (int i = 1; i < tranX; i++) {
			neuralNetwork.tranData[i] = (Math.random() * 100);

			if (neuralNetwork.maxTranX < neuralNetwork.tranData[i])
				neuralNetwork.maxTranX = neuralNetwork.tranData[i];
			if (neuralNetwork.minTranX > neuralNetwork.tranData[i])
				neuralNetwork.minTranX = neuralNetwork.tranData[i];
		}
		System.out.print("\n\n");
	}

	// 分配測試資料
	public static void initTestMatrix(NeuralNetwork1 neuralNetwork) {
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
		// System.out.print("\n\n");
	}

	// 初始化權重
	public static void initWeight(NeuralNetwork1 neuralNetwork) {
		neuralNetwork.tranWeight1 = new double[tranX][3];
		neuralNetwork.tranWeight2 = new double[tranX][3];
		neuralNetwork.d1 = new double[tranX][3];

		for (int i = 0; i < tranX; i++) {
			for (int j = 0; j < 3; j++) {
				neuralNetwork.tranWeight1[i][j] = Math.random() * 100;
				neuralNetwork.tranWeight2[i][j] = Math.random() * 100;
				neuralNetwork.d1[i][j] = 1;

			}
		}
		// System.out.println("初始： "+neuralNetwork.tranWeight2[0][0]);
	}

	// 正規化資料
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

	// 第二層
	public static void layer2(NeuralNetwork1 neuralNetwork) {
		neuralNetwork.funcTranQ = new double[tranX][3];
		neuralNetwork.outputTranQ = new double[tranX][3];
//		System.out.println("layer2");
		for (int i = 0; i < neuralNetwork.tranX; i++) {
			for (int j = 0; j < 3; j++) {
				neuralNetwork.funcTranQ[i][j] = neuralNetwork.tranData[i] * neuralNetwork.tranWeight1[i][j]
						+ neuralNetwork.d1[i][j];
				neuralNetwork.outputTranQ[i][j] = (Math.exp(neuralNetwork.funcTranQ[i][j])
						- Math.exp(-neuralNetwork.funcTranQ[i][j]))
						/ (Math.exp(neuralNetwork.funcTranQ[i][j]) - Math.exp(-neuralNetwork.funcTranQ[i][j]));
//				System.out.print(neuralNetwork.tranWeight1[i][j] + " ");
			}
		}
//		System.out.print("\n\n");
	}

	// 第三層
	public static void layer3(NeuralNetwork1 neuralNetwork) {
//		System.out.println("實際值");
		neuralNetwork.actualValue = new double[tranX];
		for (int i = 0; i < neuralNetwork.tranX; i++) {
			for (int j = 0; j < 3; j++) {
				neuralNetwork.actualValue[i] = neuralNetwork.outputTranQ[i][j]
						+ neuralNetwork.outputTranQ[i][j] * neuralNetwork.tranWeight2[i][j];
			}
//			System.out.print(neuralNetwork.actualValue[i] + "  ");
		}
//		System.out.print("\n\n");
	}

	// 尋找錯誤
	public static void findError(NeuralNetwork1 neuralNetwork) {
		// System.out.println("錯誤值");
		neuralNetwork.errorValue = new double[tranX];
		for (int i = 0; i < neuralNetwork.tranX; i++) {
			neuralNetwork.errorValue[i] = neuralNetwork.expectValue[i] - neuralNetwork.actualValue[i];
			// System.out.print(neuralNetwork.errorValue[i]+" ");
		}
		// System.out.print("\n\n");
	}

	public static void updateMessenger(NeuralNetwork1 neuralNetwork) {
		for (int i = tranX - 1; i >= 0; i--) {
			for (int k = 0; k < 1000; k++) {
				for (int j = 0; j < 3; j++) {
					neuralNetwork.tranWeight2[i][j] = neuralNetwork.tranWeight2[i][j]
							+ learnRate * neuralNetwork.errorValue[i] * neuralNetwork.outputTranQ[i][j];

					neuralNetwork.d1[i][j] = neuralNetwork.d1[i][j] + learnRate * neuralNetwork.errorValue[i]
							* neuralNetwork.tranWeight2[i][j] * Math.pow(4 / (Math.exp(neuralNetwork.funcTranQ[i][j]))
									- Math.exp(-neuralNetwork.funcTranQ[i][j]), 2);

					neuralNetwork.tranWeight1[i][j] = neuralNetwork.d1[i][j] + learnRate * neuralNetwork.errorValue[i]
							* neuralNetwork.tranWeight1[i][j] * Math.pow(4 / (Math.exp(neuralNetwork.funcTranQ[i][j]))
									- Math.exp(-neuralNetwork.funcTranQ[i][j]), 2)
							* neuralNetwork.tranData[i];
				}
				layer2(neuralNetwork);
				layer3(neuralNetwork);
				findError(neuralNetwork);
				System.out.println(neuralNetwork.actualValue[i]);
			}
		}
	}
}
