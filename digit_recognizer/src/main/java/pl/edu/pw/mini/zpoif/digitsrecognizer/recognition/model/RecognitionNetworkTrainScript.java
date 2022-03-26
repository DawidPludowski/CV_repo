package pl.edu.pw.mini.zpoif.digitsrecognizer.recognition.model;

import java.io.File;
import java.io.IOException;

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecognitionNetworkTrainScript {
	
	public static void main(String[] args) throws IOException {
		
		final int rngSeed = 123;
		final int numOfInputs = 28*28;
		final int numOfOutputs = 10;
		final int hiddenLayerSize = 1000;
		
		final int batchSize = 125;
		final int numEpochs = 15;
		
		final Logger logger = LoggerFactory.getLogger(RecognitionNetworkTrainScript.class);

		
		logger.info("Loading MNIST dataset...");
		DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize, true, rngSeed);
		DataSetIterator mnistTest = new MnistDataSetIterator(batchSize, false, rngSeed);

		logger.info("Configuring network...");
		MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder().
				seed(rngSeed).
				updater(new Nesterovs(0.006, 0.9)).
				l2(1e-4).
				list().
					layer(0, new DenseLayer.Builder().
							nIn(numOfInputs).
							nOut(hiddenLayerSize).
							activation(Activation.RELU).
							weightInit(WeightInit.XAVIER).
							build()).
					layer(1, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD).
							nIn(hiddenLayerSize).
							nOut(numOfOutputs).
							activation(Activation.SOFTMAX).
							weightInit(WeightInit.XAVIER).
							build()).
				build();
		
		logger.info("Initializing model...");
		MultiLayerNetwork model = new MultiLayerNetwork(conf);
		model.init();
		
		model.setListeners(new ScoreIterationListener(500));
		
		logger.info("Fitting model...");
		model.fit(mnistTrain, numEpochs);
		
		logger.info("Evaluating test...");
        Evaluation eval = model.evaluate(mnistTest);
        logger.info(eval.stats());
		
        logger.info("Saving to file...");
        //model.save(new File("networks/network"));
		
        logger.info("Complete");
	}
}
