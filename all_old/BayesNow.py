'''
Name: Shuai Wang
Andrew ID: shuaiw1
Reference: http://ebiquity.umbc.edu/blogger/2010/12/07/naive-bayes-classifier-in-50-lines/
Comment: Method structure and understanding 
'''
from __future__ import division
import collections
import math
import sys
 
class NBModel: 
        # Initialize argment from standard input
        def __init__(self, trainingData, testData, outputFileName, featureFileName):            
                self.trainingFile = trainingData
                self.testFile = testData
                self.featureFile = featureFileName
                self.features = {}              #All feature names and their possible values (including the class label)
                self.featureNameList = []       #Maintain the order of features as in the feature file
                self.featureCounts = collections.defaultdict(lambda: 1)    #Contains tuples of the form (label, feature_name, feature_value)
                self.featureVectors = []        #Contains all the values and the label class as the first entry
                self.labelCounts = collections.defaultdict(lambda: 0)      #Initial label class count based on default english dictionary
                self.wrong = 0                  #Classified wrong
                self.correct = 0                #Classified right
                self.outputFile = open(outputFileName, 'w')    
                self.yes = 0
                self.no = 0
        #Set values to train parameters including feature vectore and training data                          
        def GetValues(self):   
               file = open(self.featureFile, 'r')
               for line in file:
                    if (line.strip() !='') and (not line.startswith('Feature_Name')): #Strip first line header 
                        self.featureNameList.append(line.strip().split()[0])          #Get all feature names  
                        self.features[self.featureNameList[len(self.featureNameList)-1]] = line[line.find('{')+1: line.find('}')].strip()
                   
               file = open(self.trainingFile, 'r')               
               for line in file:
                    self.featureVectors.append(line.lower().split('\t'))             #Set up feature vectors
               file.close
               #print len(self.featureVectors)
        #Train the naive bayes classifier based on data      
        def TrainClassifier(self):
                for fv in self.featureVectors:
                    self.labelCounts[fv[0]] += 1  #Increment count of the label class                       
                    for counter in range(0, len(fv)-1):  #Counts the number of co-occurrences of each feature value with each class label
                        self.featureCounts[(fv[0], self.featureNameList[counter-1], fv[counter])] += 1 #Stores the number in the form of 3-tuples
                                             
                for label in self.labelCounts:  #First feature is the label class
                    for feature in self.featureNameList[:len(self.featureNameList)-1]:
                        self.labelCounts[label] += len(self.features[feature]) #The counts adjusted by incrementing these counts by the total number of observations.
                                               
        def Classify(self, featureVector): #Method to classify test data
        #Feature vector is a simple list like the ones that we use to train the classifier
                probabilityPerLabel = {}  #Final computed probabilities for each label 
                totalProbability = 0
                for label in self.labelCounts:
                        logProb = 0
                        for featureValue in featureVector: #Compute the product of MLE for each label
                                if featureVector.index(featureValue) == 0:
                                    continue 
                                # Use log function plus which is the same with multiply probabilities                            
                                logProb += math.log(self.featureCounts[(label, self.featureNameList[featureVector.index(featureValue)-1], featureValue)]/self.labelCounts[label])
                        pProbability = (self.labelCounts[label]/sum(self.labelCounts.values()))
                        probabilityPerLabel[label] = pProbability * math.exp(logProb) # Get the real probablity value from log value
                        #self.outputFile.write( "Prior probability for class "+ label+ ": " + str(pProbability) + "\n")
                        totalProbability += probabilityPerLabel[label]
                #self.outputFile.write("TP:" + " " + str(totalProbability) + "\n") 
                self.outputFile.write("Likelihood for Yes or No:" + " " + str(probabilityPerLabel) + "\n")  
                self.outputFile.write("Probability of collaboration:" + " " + (str(100*probabilityPerLabel["y"]/totalProbability)) + "%" + "\n")             
                return max(probabilityPerLabel, key = lambda classLabel: probabilityPerLabel[classLabel]) #Entry that has the highest probability
                                      
        def TestClassify(self): #Method to test the classifier from test file
                file = open(self.testFile, 'r')
                for line in file:   #Initialize all parameters needed including feature vector
                    self.outputFile.write("-----------------------------------" + "\n")
                    vector = line.strip().lower().split('\t')                                
                    self.outputFile.write("Similarity of research areas: " + str(vector[0])+ "\n")
                    self.outputFile.write("Similarity of author reputation: " + str(vector[1]) + "\n")
                    self.outputFile.write("Author connectedness: " + str(vector[2])+ "\n") 
                    self.outputFile.write("Collaboration history of each author: "+ str(vector[3])+"\n")
                    assigned = self.Classify(vector)                                
                    if assigned == "y":                                
                        self.outputFile.write("Authors are likely to collaborate"+ "\n")
                    else:
                        self.outputFile.write("Authors are not likely to collaborate" + "\n")

                    if "y" == str(vector[0]):
                        self.yes += 1
                    else:
                        self.no += 1

                    if assigned == vector[0]:
                        self.correct +=1
                    else:
                        self.wrong +=1
                self.outputFile.write("--------------------------" + "\n")
                self.outputFile.write("Total test number: " + str(self.yes + self.no) + "\n")
                self.outputFile.write("Recall: " + str(100*(self.correct/(self.wrong + self.correct)))+" %")

if __name__ == "__main__":            
            if len(sys.argv) != 5:   # Input number error check
                sys.stderr.write('Input Format: <TrainingFile> <TestFile> <OutputFile> <FeatureFile>')
                sys.exit(3)        
            model = NBModel(sys.argv[1],sys.argv[2],sys.argv[3],sys.argv[4])
            model.GetValues()
            model.TrainClassifier()
            model.TestClassify()