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
	public double maxTranX, minTranX, outputR, error;
	public double maxTestX, minTestX;
	public double[] expectValue, tranActualValue, testActualValue, errorValue;
	public static double[][] funcTranQ, outputTranQ, tranWeight1, tranWeight2;
	public static double[][] functionTestQ, outputTestQ, testWeight1, testWeight2, d1;

	public static void main(String[] args) {
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
		trainingData(neuralNetwork);
		testingData(neuralNetwork);
	}

	// 分配訓練資料
	public static void initTranMatrix(NeuralNetwork1 neuralNetwork) {
		System.out.println("初始化訓練資料");

		neuralNetwork.funcTranQ = new double[1][3];
		neuralNetwork.outputTranQ = new double[1][3];
		neuralNetwork.errorValue = new double[tranX];

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
		neuralNetwork.tranWeight1 = new double[1][3];
		neuralNetwork.tranWeight2 = new double[1][3];
		neuralNetwork.d1 = new double[1][3];

		for (int j = 0; j < 3; j++) {
			neuralNetwork.tranWeight1[0][j] = Math.random();
			neuralNetwork.tranWeight2[0][j] = Math.random();
			neuralNetwork.d1[0][j] = Math.random();

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
		neuralNetwork.testActualValue = new double[testX];
		System.out.println("期望值");
		System.out.println("訓練");
		for (int i = 0; i < neuralNetwork.tranX; i++) {
			neuralNetwork.expectValue[i] = 2 * neuralNetwork.tranData[i];
			System.out.print(neuralNetwork.expectValue[i] + "  ");
		}
		System.out.println("期望值");
		System.out.println("測試");
		for (int i = 0; i < neuralNetwork.testX; i++) {
			neuralNetwork.testActualValue[i] = 2 * neuralNetwork.testData[i];
			System.out.print(neuralNetwork.testActualValue[i] + "  ");
		}
		System.out.print("\n\n");
	}

	public static void forward(NeuralNetwork1 neuralNetwork, double data) {
		double sum = 0;

		for (int i = 0; i < 3; i++) {
			neuralNetwork.funcTranQ[0][i] = data * neuralNetwork.tranWeight1[0][i];
			neuralNetwork.outputTranQ[0][i] = (Math.exp(neuralNetwork.funcTranQ[0][i])
					- Math.exp(-neuralNetwork.funcTranQ[0][i]))
					/ (Math.exp(neuralNetwork.funcTranQ[0][i]) + Math.exp(-neuralNetwork.funcTranQ[0][i]));
			sum = sum + neuralNetwork.outputTranQ[0][i] * neuralNetwork.tranWeight2[0][i];
		}
		neuralNetwork.outputR = sum;
	}

	public static void backward(NeuralNetwork1 neuralNetwork, double tranData, double expectValue) {
		double errorValue = 0;
		neuralNetwork.error = expectValue - neuralNetwork.outputR;
		double[][] tempWeight2 = copyMatic(neuralNetwork.tranWeight2);
		double[][] tempD1 = copyMatic(neuralNetwork.d1);
		double[][] tempWeight1 = copyMatic(neuralNetwork.tranWeight1);
		for (int i = 0; i < 3; i++) {
			neuralNetwork.tranWeight2[0][i] = neuralNetwork.tranWeight2[0][i]
					+ neuralNetwork.learnRate * neuralNetwork.error * neuralNetwork.outputTranQ[0][i];
			neuralNetwork.d1[0][i] = tempD1[0][i]
					+ neuralNetwork.learnRate * neuralNetwork.error * tempWeight2[0][i] * (4 / Math.pow(
							(Math.exp(neuralNetwork.funcTranQ[0][i]) + Math.exp(-neuralNetwork.funcTranQ[0][i])), 2));
			neuralNetwork.tranWeight1[0][i] = neuralNetwork.tranWeight1[0][i] + (neuralNetwork.learnRate * neuralNetwork.error
					* tempWeight2[0][i]
					* (4 / Math.pow(
							(Math.exp(neuralNetwork.funcTranQ[0][i]) + Math.exp(-neuralNetwork.funcTranQ[0][i])), 2))
					* neuralNetwork.outputTranQ[0][i]);
		}
	}

	public static double[][] copyMatic(double matrixA[][]) {
		double[][] retMatrix = new double[1][3];

		for (int i = 0; i < 3; i++) {
			retMatrix[0][i] = matrixA[0][i];
		}

		return retMatrix;
	}

	public static void trainingData(NeuralNetwork1 neuralNetwork) {
		int tran = 1000;
		double sum = 0;

		for (int i = 0; i < tran; i++) {
			for (int j = 0; j < neuralNetwork.tranX; j++) {
				forward(neuralNetwork, neuralNetwork.tranData[j]);
				backward(neuralNetwork, neuralNetwork.tranData[j], neuralNetwork.expectValue[j]);
				neuralNetwork.errorValue[j] = neuralNetwork.error;
			}

			for (int j = 0; j < neuralNetwork.errorValue.length; j++) {
				sum += Math.pow(neuralNetwork.errorValue[j], 2);
			}
			sum /= neuralNetwork.errorValue.length;
			sum = Math.sqrt(sum);
			System.out.println("Training RMS(error): " + sum);
		}
	}

	public static void testingData(NeuralNetwork1 neuralNetwork) {
		double sum = 0;
		int i;
		double[] tempError = new double[neuralNetwork.testX];
		for (i= 0; i < neuralNetwork.testX; i++) {
			forward(neuralNetwork, neuralNetwork.testData[i]);
			tempError[i] = neuralNetwork.testActualValue[i] - neuralNetwork.outputR;
			sum += Math.pow(tempError[i], 2);

			
		}
		System.out.println("Test RMS(error): " + Math.sqrt(sum / (i+1)));
	}
}
