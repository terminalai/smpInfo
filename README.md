# Gait Monitoring and Analysis for Parkinson's Disease Patients
By: Nallapuraju Ananya, Ye Chen Rui and Prannaya Gupta

<p align="center">
  <img src="./logos/Gait%20Monitoring%20and%20Analysis%20for%20Parkinson's%20Patients%20Logo.png" width="438" height="439" alt="Gait Monitoring and Analysis for Parkinson's Disease Patients">
</p>


**Research Mentor**: Professor Arthur Tay


**School Mentor**: Mr Lim Yeow Heng

To monitor gait patterns to detect freezing of gait. Done as part of Singapore Science Mentorship Programme.

## Table of Contents
- [Gait Monitoring for Parkinson's Disease Patients](#gait-monitoring-for-parkinson-s-disease-patients)
  * [Project Description](#project-description)
    + [Final Product](#final-product)
    + [Methodology](#methodology)
  * [Introduction](#introduction)
    + [Parkinson’s Disease and Freezing of Gait](#parkinson-s-disease-and-freezing-of-gait)
    + [Contribution](#contribution)
  * [Related work](#related-work)
    + [Utilisation of IMUs in analysis](#utilisation-of-imus-in-analysis)
    + [Machine learning approaches to detecting PD](#machine-learning-approaches-to-detecting-pd)
    + [Gait parameters to be analysed](#gait-parameters-to-be-analysed)
  * [GaitDB Dataset](https://www.physionet.org/content/gaitdb/1.0.0/)
    + [Data Description](#data-description)
    + [Method Used](#method-used)
    + [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/GaitDB%20Analysis.ipynb)
  * [Daphnet Dataset](https://archive.ics.uci.edu/ml/datasets/Daphnet+Freezing+of+Gait)
    + [Data Description](#data-description-1)
    + [Data Attributes](#data-attributes)
      - [Annotations](#annotations)
    + [LICENCE](#licence)
    + [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb)
    + [Set-Up and Imports](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Set-Up-and-Imports)
    + [Get Data](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Get-Data)
    + [Plots](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Plots)
    + [Signal Processing Algorithm](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Signal-Processing-Algorithm)
      - [Subject Identification and Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Subject-Identification-and-Analysis)
      - [Getting Hypothesis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb#Getting-Hypothesis)
  * [VGRF Dataset](https://physionet.org/content/gaitpdb/1.0.0/)
    + [Data Description](#data-description-2)
    + [Data format](#data-format)
    + [Data file names](#data-file-names)
    + [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/VGRF%20Analysis.ipynb)
  * [References](#references)



## Project Description
The objective of this project is to monitor the gait patterns for people with Parkinson Disease. We will analyse the gait for PD patients during freezing and non-freezing (normal) events. Various parameters (e.g. cadence, step lengths, stride lengths, etc) related to gait will be derived from motion sensors (accelerometers, gyroscopes, magnetometers) using public datasets of PD patients. We will then investigate which of these parameters is most suitable for classification for freezing of gait in PD patients.

### Final Product
To make use of signal processing algorithms in extracting gait parameters from motion sensors and identifying most suitable parameters for classification of freezing of gait in PD patients.

### Methodology
Students will first need to review and understands how to extract gait parameters from motion sensors. There are different algorithms in the literature and they will need to investigate which is most suitable. They will then make use of a PD patient public dataset to test out their methods. If time permits, they will learn basic machine learning tools for classification.

## Abstract

Parkinson’s disease (PD) is a neurodegenerative disorder that affects the dopamine producing neurons in the substantia nigra, an area of the brain, leading to shaking, stiffness and difficulty walking. Parkinson’s patients frequently exhibit the debilitating condition freezing of gait (FOG), which is when patients cannot move their feet forward despite the intention to walk. While the feet remain in place, the torso still has forward momentum, making falls very common. At the start, FOG can be triggered by stress, tight spaces or a sudden change in direction. As the disease progresses, this happens more frequently, a fact extremely detrimental to the patient’s health and mental well-being.

## Introduction

Parkinson’s Disease (PD) is a progressive nervous system disorder that affects the basal ganglia, resulting in the progressive loss of dopamine neurotransmitters and poorer connection between the central nervous system and muscles<sup>[1]</sup>. This leads to many gait abnormalities, mainly tremors, bradykinesia, rigidity, and a loss of postural reflexes. Secondary motor symptoms that are evoked include stride length reduction, shuffling gait, step festination and freezing<sup>[2,3]</sup>. Freezing of Gait (FOG) is one of the most debilitating effects of PD and is defined as a brief, episodic absence or marked reduction of forward progression of the feet despite the intention to walk<sup>[3]</sup>. It has serious social and clinical consequences for patients as it leads to falls<sup>[4]</sup>, interferes with daily activities and significantly impacts quality of life<sup>[5]</sup>. While FOG can happen anytime, it happens most often during turns, before gait initiation, in tight quarters such as doorways and in stressful situations<sup>[6]</sup>. It is triggered by visual stimulation.

Currently, FOG characterization is done using two main methods. In the first method, FOG is quantified by gait tests such as the timed up and go (TUG) test and the Hoehn and Yahr (H&Y) scale. The TUG test measures in seconds how long a patient takes to stand up from their chair, walk 3m past a line, turn around, walk back and sit down<sup>[7]</sup>. On the other hand, the H&Y scale has 5 stages to indicate levels of disability<sup>[8]</sup>: 

- Stage 1: Symptoms on one side of the body only. 
- Stage 2: Symptoms on both sides of the body; no impairment of balance. 
- Stage 3: Balance impairment; mild to moderate disease; physically independent. 
- Stage 4: Severe disability, but still able to walk or stand unassisted. 
- Stage 5: Wheelchair-bound or bedridden unless assisted.

In the second method, individual questionnaires are used. The Freezing Of Gait Questionnaire (FOG-Q) is a notable one, comprising 6 questions and utilising a 5-point scale to rank symptom severity<sup>[9]</sup>. 

However, both methods are highly inefficient in measuring FOG as it is highly sensitive to environmental triggers, medication and the patient’s mental state. Therefore, there has been research into using wearable inertial measurement units (IMUs) to display exactly the gait types of PD patients. 

## Aims and Objectives

This study aims to compare all the ways of measuring FOG and determine the best parameter to utilise while creating an algorithm for data analysis. The comfort of the patient and the ease and accuracy in which the parameter can predict FOG will be taken into account in this study. Ultimately, a prototype that fulfils all these requirements will be made.

## Literature Review

### _Utilisation of IMUs in analysis_
This section reports previous studies which have explored the application of motion sensors on PD patients to accurately predict FOG. Ferster et al.<sup>[3]</sup> placed 9-axis IMUs (comprising 3D accelerometers, 3D gyroscopes and 3D magnetometers) on both ankles of the subjects to extract gait features such as stride length and stride duration. Moreover, as FOG exhibits unique frequency ranges, they introduce and discuss frequency features such as dominant frequency, dominant frequency amplitude and the inverse of the dominant frequency slope of the acceleration data to quantify changes in gait quality. Ferster was able to show specific changes in the stride duration, stride length, dominant frequency and the inverse of the dominant frequency slope with up to four seconds prior to FoG on all subjects. 

Baechlin et al.<sup>[10]</sup> proposed placing accelerometers at three different parts of the body: the shank, thigh and lower back, where the wearable computer is attached to.

<p align="center">
  <img src="./descriptions/daphnet/diagram.PNG" alt="baechlin" width="640"><br/>
  <b>Figure 1: The Baechlin et al. 3D Accelerometer Wearable System</b>
</p>

Alam et al.<sup>[11]</sup> analyzed the vertical ground reaction force using force insoles in patients’ shoes to display gait cycles. Pinto et al.<sup>[12]</sup> again utilised accelerometers and gyroscopes to determine stride time, this time placing the accelerometer at the shank. 

Based on our research, we were able to consolidate a list of the main IMUs that we were to consider as follows:

| IMU | Purpose | Measured Parameter |
| --- | --- | --- |
| Accelerometer | Measuring acceleration | Stride Length, Stride Duration |
| Gyroscope | Measuring angular velocity | Step Festination, Gait Asymmetry |
| Flexible Goniometer | Measuring body joint angles | Flat Foot Strike |
| Force- sensitive Insole | Measuring the tension and compression forces that act on the sensor | Gait Cycle (not accurate for PD patients who suffer from flat footedness) |


Many works have also tried utilising motion capture systems to annotate FOG events, synchronising sensor data and computer analysis to make way for machine learning algorithms. Kuhner et al.<sup>[13]</sup> performed this experiment, setting up 12 cameras as well as utilising an inertial measurement suit to create a ‘live’ system that reduces the latency of data processing.

The above literature confirms the success of utilising accelerometers, gyroscopes and force insoles to effectively differentiate FOG from normal gait and has greatly helped in designing the proposed approach for this study.

### _Machine learning approaches to detecting PD_
Aich et al.<sup>[14]</sup> have done a comprehensive review of the following four different types of machine learning algorithms in classifying patients with FOG or no FOG: 

- Support Vector Machine (SVM)
  * Considered the most accurate by Aich et al<sup>[14]</sup>.
- k-Nearest Neighbour (kNN)
- Decision Tree (DT)
- Naïve Bayes (NB)
- Threshold/ Logistic Regression
- Time Series Analysis


They found that the SVM classifier with radial basis function provides the highest accuracy of 91.42% as well as the highest sensitivity and specificity of 90.89% and 91.21% respectively. 

Eom et al.<sup>[15]</sup> used thresholds to classify FOG and non-FOG states. The number of thresholds varied, increasing by two for every dimension added. The range between two thresholds was used to determine FOG states for 1D data while six thresholds were required for 3D data. Additionally, three FOG states (FOG: 1, non-FOG: 0) were “AND” operated to determine final FOG.

### _Gait parameters to be analysed_
The frequency-based features discussed by Baechlin et. al<sup>[10]</sup> are reliable, but they have one major drawback. The large amount of calculations required to perform the Fast Fourier Transform (FFT) algorithm needed to analyse frequency-based data means that a digital signal processing chip is required, making it impossible to fit in a small, lightweight unobtrusive system. This would make it impractical for real life detection. For the patients with gait problems, a bulky and obtrusive system may worsen the gait disturbances. Additionally, most experiments utilised a relatively long window length (4 seconds<sup>[10]</sup>, 6 seconds<sup>[16]</sup>, and 10 seconds<sup>[17]</sup>) for detection, leading to a long delay in FOG detection. Eom et al.<sup>[15]</sup> suggested a simple and fast time-domain method for FOG detection that was comparable to the traditional frequency-domain method with a calculation load of 1,154 times less. This has practical clinical applications.

Previous researchers also have reported the importance of five parameters for the detection of PD. Most notably, Hollman et al.<sup>[18]</sup> have proposed five major domains of gait based on factor analysis:

1. Rhythm - step and stride time
2. Phase - gait cycle
3. Variability - step-to-step variability
4. Pace - gait speed, stride and step length
5. Base of support - step width.

Others have confirmed the importance of determining the spatiotemporal parameters. Alcock et al.<sup>[19]</sup>, Coste et al.<sup>[20]</sup> and Schlachetzki et al.<sup>[21]</sup> have all discussed the importance of stride length, stride time and gait velocity in distinguishing PD patients from healthy older adults.

Overall, the following is a summary of gait parameters found in our research.

| Gait abnormalities | Definition |
| --- | --- | --- |
| Step Festination | Shortening of steps |
| Decrease in Stride Duration | Decrease in the time difference between two consecutive detected acceleration peaks of the same leg |
| Postural Instability | Loss of balance |
| Increase in step-to-step time variability | Difference in time taken between each stride |
| Stride Length Reduction | Reduction in distance between each stride/step |
| Decreased Cadence | Decrease in number of steps taken per unit time |
| Gait Asymmetry | Difference in gait between the two legs of patient |


Hence, in this study, the focus will be on gait velocity, stride time and gait cycle. They will be compared by analysing acceleration data and vertical ground reaction force (VGRF) data from three sources.

## Methodology

### _DAPHNet Dataset_
In our main investigation, we employed the DAPHNet Dataset, which is the result of a study done by Baechlin et al.<sup>[10]</sup>, carried out by the Laboratory for Gait and Neurodynamics, Department of Neurology, Tel Aviv Sourasky Medical Center (TASMC). In this experiment, 17 samples were derived frrom 10 PD patients with varying H&Y scales who were made to do various walking tasks, including walking back and forth in a straight line, doing several 180 degrees turns, random walking including a series of initiated stops and 360 degree turns and walking simulating daily activities. Daily activities refer to entering and leaving rooms, getting something to drink and returning to the starting room with the cup of water. 

Data was recorded using three 3D acceleration sensors attached to the shank, thigh and the lower back of each subject. The sensors recorded at 64Hz and transmitted the acceleration data via a Bluetooth link to a wearable computing system that was located at the lower back of the subjects. Patient data is summarised in a table below.

<p align="center">
  <img src="./descriptions/daphnet/demographics.PNG" alt="demographics" width="640"><br/>
  <b>Figure 1. Table showing gender, age, disease duration and H&Y scale of PD patients in DAPHNet dataset</b>
</p>

However, there are limitations to the dataset. Patient 08 and Patient 01 both suffered from walking difficulties due to disease severity and foot drop respectively. Hence, the system was unable to distinguish between walking periods and very short freezing events.

### _Signal Processing_
This study uses signal processing algorithms to compute a postulated freeze index. Moore et al.<sup>[23]</sup> defined the freeze index (FI) as the power ratio of freeze band (0.5–3.0 Hz) to locomotor band (3–8 Hz) derived from the frequency spectrum and identified FOG episodes at the time periods when FI exceeds a certain threshold. The code uses an updated form of Moore’s algorithm, where it calculates the Freeze Index from data from a specific axis (horizontal forward, horizontal lateral and vertical) from the sensors on the thigh by performing windowing of data with a length of 256 data points and steps up 32 data points. After performing a mean normalisation, the code finds the Fast Fourier Transform of the normalised window and uses that to compute the Freeze Index and supposed Power Spectral Density (PSD). After this, it accounts for a standing case where the PSD<sub>threshold</sub> = 2<sup>11.5</sup>, and in cases where it is below this threshold, the freeze index becomes zero. From this, we had the final postulated freeze index.

In this experiment, we decided to define a final function `freeze(N)`, where `freeze(N) = Freeze Index postulated from N-axis acceleration`. For the investigation, we defined the axes as follows:

| Axis | Axis with respect to the user |
| --- | --- |
| X | Horizontal Forward |
| Y | Vertical |
| Z | Horizontal Vertical |


### _SVM Analysis_
Unlike Baechlin et al.<sup>[10]</sup>, where the Freeze Index with a threshold of 1.5 is used such that a freeze index greater than the threshold is considered FOG, we decided to follow the results found by Aich et al<sup>[14]</sup>, and hence decided to test SVM models against the data we had derived. 

Due to the large amount of data amassed from the signal processing algorithms, we decided to use the Google Colaboratory Tool<sup>[23]</sup>, in which we could utilise Graphics Processing Units (GPUs) of more powerful computers at Google. This allowed us to run the SVM much faster such that we could get the results for the investigation. We also used the ThunderSVM utility<sup>[24]</sup> developed by the Xtra Computing Group at the NUS School of Computing (SoC).

For the investigation, we employed the Linear and Gaussian Kernels and tested these SVMs with either 2-axial freeze indices (i.e. taking freeze indices from 2 of the 3 axes defined) or 3-axial freeze indices (which took into account freeze(X), freeze(Y) and freeze(Z)).

### _Arduino Program_
Our Program was developed using Arduino's `.ino` programming language based off the C++ programming language. To get the best SVM model, we used a program called sklearn-porter<sup>[25]</sup> developed by Darius Morawies, which converted the trained SVM model to C code which was saved as a `model.h` file. This model was accessed by the Arduino Program, which also computed the freeze values.

This study utilises an Arduino Nano 33 BLE board that is attached to an elastic strap. It contains a 9-axial IMU, comprising a 3D accelerometer, 3D gyroscope and 3D magnetometer. In our program, upon a certain freeze event predicted by the SVM, the built-in light-emitting diode (LED).

---

## [GaitDB Dataset](https://www.physionet.org/content/gaitdb/1.0.0/)

### Data Description

Walking stride interval time series included are from 15 subjects: 5 healthy young adults (23 - 29 years old), 5 healthy old adults (71 - 77 years old), and 5 older adults (60 - 77 years old) with Parkinson's disease. The file name indicates old (o), young (y) or Parkinson's disease (pd). For the old and young subjects, the age (in years) is also included in the filename.

Subjects walked continuously on level ground around an obstacle-free path. The stride interval was measured using ultra-thin, force sensitive resistors placed inside the shoe. The analog force signal was sampled at 300 Hz with a 12 bit A/D converter, using an ambulatory, ankle-worn microcomputer that also recorded the data. Subsequently, the time between foot-strikes was automatically computed. The method for determining the stride interval is a modification of a previously validated method that has been shown to agree with force-platform measures, a “gold” standard.

Data were collected from the healthy subjects as they walked in a roughly circular path for 15 minutes, and from the subjects with Parkinson’s disease as they walked for 6 minutes up and down a long hallway.

### Method Used
We calculated the variance of the data and were able to find a correlation between the variance and the diagnosis. 

![variance](images/graphs/varianceOfStrideTime.png)

Based on this result, we employed a rather simplistic classification algorithm using Logistic Regression to find a value that corresponded. 

The result was not as accurate as would be reliable, but that was mainly due to some corner cases. Below are the results.

| Precision | Recall or Sensitivity | Specificity | Accuracy | F1 Score |
| --- | --- | --- | --- | --- |
| 0.80 | 0.80 | 0.90 | 0.867 | 0.80 |

### [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/GaitDB%20Analysis.ipynb)


## [Daphnet Dataset](https://archive.ics.uci.edu/ml/datasets/Daphnet+Freezing+of+Gait)
(suggested by Prof Tay)

### Data Description

The Daphnet Freezing of Gait Dataset Freezing of Gait in users with Parkinson disease (hereafter Daphnet Freezing of Gait Dataset) is a dataset devised to benchmark automatic methods to recognize gait freeze from wearable acceleration sensors placed on legs and hip.

The dataset was recorded in the lab with emphasis on generating many freeze events. Users performed there kinds of tasks: straight line walking, walking with numerous turns, and finally a more realistic activity of daily living (ADL) task, where users went into different rooms while fetching coffee, opening doors, etc.

This dataset is the result of a collaboration between the Laboratory for Gait and Neurodynamics, Tel Aviv Sourasky Medical Center, Israel and the Wearable Computing Laboratory, ETH Zurich, Switzerland. Recordings were run at the Tel Aviv Sourasky Medical Center in 2008. The study was approved by the local Human Subjects Review Committee, and was performed in accordance with the ethical standards of the Declaration of Helsinki.

This dataset was collected as part of the EU FP6 project Daphnet, grant number 018474-2.
Additional effort to publish this dataset was supported in part by the EU FP7 project CuPiD, grant number 288516.

### Data Attributes
1. Time of sample in millisecond
2. Ankle (shank) acceleration - horizontal forward acceleration (mg)
3. Ankle (shank) acceleration - vertical (mg)
4. Ankle (shank) acceleration - horizontal lateral (mg)
5. Upper leg (thigh) acceleration - horizontal forward acceleration (mg)
6. Upper leg (thigh) acceleration - vertical (mg)
7. Upper leg (thigh) acceleration - horizontal lateral (mg)
8. Trunk acceleration - horizontal forward acceleration (mg)
9. Trunk acceleration - vertical (mg)
10. Trunk acceleration - horizontal lateral (mg)
11. Annotations (see Annotations section)

#### Annotations
The meaning of the annotations are as follows:

- **0** - not part of the experiment. For instance the sensors are installed on the user or the user is performing activities unrelated to the experimental protocol, such as debriefing
- **1** - experiment, no freeze (can be any of stand, walk, turn)
- **2** - freeze

### LICENCE

Use of this dataset in publications must be acknowledged by referencing the following publication:

Marc Bächlin, Meir Plotnik, Daniel Roggen, Inbal Maidan, Jeffrey M. Hausdorff, Nir Giladi, and Gerhard Tröster, Wearable Assistant for Parkinson's Disease Patients With the Freezing of Gait Symptom. IEEE Transactions on Information Technology in Biomedicine, 14(2), March 2010, pages 436-446

This paper describes the dataset in details. It explain the data acquisition protocol, the kind of sensor used and their placement, and the nature of the data acquired. It also provides baseline results for the automated detection of freezing of gait, against which newer methods can be benchmarked. In particular it describes detection sensitivity/specificity for 3 sensor placements and 4 kinds of derived sensor signals, it analyzes detection latency, and provides first insight into user specific v.s. user independent performance.

We also appreciate if you inform us (daniel.roggen@ieee.org) of any publication using this dataset for cross-referencing purposes.

### [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/Daphnet%20Analysis.ipynb)

## [VGRF Dataset](https://physionet.org/content/gaitpdb/1.0.0/)
### Data Description

Parkinson's disease (PD) is one of the most common movement disorders, affecting approximately 1 million Americans (estimates range between 4 and 6.5 million people worldwide) and about 1% of older adults. In the US alone, 60,000 new cases are diagnosed each year. PD is a chronic and progressive neurological disorder that results in tremor, rigidity, slowness, and postural instability. A disturbed gait is a common, debilitating symptom; patients with severe gait disturbances are prone to falls and may lose their functional independence.

This database contains measures of gait from 93 patients with idiopathic PD (mean age: 66.3 years; 63% men), and 73 healthy controls (mean age: 66.3 years; 55% men). The database includes the vertical ground reaction force records of subjects as they walked at their usual, self-selected pace for approximately 2 minutes on level ground. Underneath each foot were 8 sensors ([Ultraflex Computer Dyno Graphy, Infotronic Inc.](http://www.infotronic.nl/#CDG)) that measure force (in Newtons) as a function of time. The output of each of these 16 sensors has been digitized and recorded at 100 samples per second, and the records also include two signals that reflect the sum of the 8 sensor outputs for each foot. For details about the format of the data, please see [this note](https://physionet.org/content/gaitpdb/1.0.0/format.txt).

With this information, one can investigate the force record as a function of time and location, derive measures that reflect the center-of-pressure as a function of time, and determine timing measures (e.g., stride time, swing time) for each foot as functions of time. Thus, one can study the stride-to-stride dynamics and the variability of these time series.

This database also includes demographic information, measures of disease severity (i.e., using the Hoehn & Yahr staging and/or the Unified Parkinson's Disease Rating Scale) and other related measures (available in HTML or xls spreadsheet format).

A subset of the database includes measures recorded as subjects performed a second task (serial 7 subtractions) while walking, as in the figure above, which shows excerpts of swing time series from a patient with PD (lower panels) and a control subject (upper panels), under usual walking conditions (at left) and when performing serial 7 subtractions (at right). Under usual walking conditions, variability is larger in the patient with PD (Coefficient of Variation = 2.7%), compared to the control subject (CV = 1.3%). Variability increases during dual tasking in the subject with PD (CV = 6.5%), but not in the control subject (CV = 1.2%). From Yogev et al. (reference [4] below).

### Data format

Each line contains 19 columns:

| Column | Description |
|--------|-------------|
| 1 | Time (in seconds)|
| 2-9 | **Vertical ground reaction force** (VGRF, in Newton) on each of 8 sensors located under the left foot |
| 10-17 |VGRF on each of the 8 sensors located under the right foot |
| 18 | Total force under the left foot |
| 19 | Total force under the right foot |


When a person is comfortably standing with both legs parallel to each
other, sensor locations inside the insole can be described (according
to the [Infotronic website](http://www.infotronic.nl/)) as lying
approximately at the following (X,Y) coordinates, assuming that the
origin (0,0) is just between the legs and the person is facing towards
the positive side of the Y axis:

| Sensor | X    | Y    |
|--------|------|------|
| L1     | -500 | -800 |
| L2     | -700 | -400 |
| L3     | -300 | -400 |
| L4     | -700 | 0    |
| L5     | -300 | 0    |
| L6     | -700 | 400  |
| L7     | -300 | 400  |
| L8     | -500 | 800  |
| R1     | 500  | -800 |
| R2     | 700  | -400 |
| R3     | 300  | -400 |
| R4     | 700  | 0    |
| R5     | 300  | 0    |
| R6     | 700  | 400  |
| R7     | 300  | 400  |
| R8     | 500  | 800  |

The X and Y numbers are in an arbitrary coordinate system reflecting
the relative (arbitrarily scaled) positions of the sensors within each
insole. During walking, the sensors inside each insole remain at the
same relative position, but the two feet are no longer parallel to
each other. Thus, this coordinate system enables a calculation of a
proxy for the location of the center of pressure (COP) under each
foot.


### Data file names
These follow a common convention, e.g., ```GaCo01_02.txt```  or  ```JuPt03_06.txt```, where

- Ga, Ju or Si – indicate the study from which the data originated:
 - Ga - Galit Yogev et al ([dual tasking in PD; Eur J Neuro, 2005](https://onlinelibrary.wiley.com/doi/abs/10.1111/j.1460-9568.2005.04298.x))
 - Ju – Hausdorff et al ([RAS in PD; Eur J Neuro, 2007](https://onlinelibrary.wiley.com/doi/abs/10.1111/j.1460-9568.2007.05810.x))
 - Si - Silvi Frenkel-Toledo et al ([Treadmill walking in PD; Mov Disorders, 2005](https://onlinelibrary.wiley.com/doi/abs/10.1002/mds.20507))

- Co or Pt: Control subject or a PD Patient respectively

- 01: Subject number in the group

- A walk number of 10  (for the "Ga" study) indicates a dual-task walking,
where the subject was engaged in serial-7 subtraction while walking.

- A walk number of 01 refers to a usual, normal walk. 

The sampling rate was 100 Hz.

### [Analysis](https://nbviewer.jupyter.org/github/ThePyProgrammer/GaitMonitoringForParkinsonsDiseasePatients/blob/main/VGRF%20Analysis.ipynb)


## References
[1] [Braak, H., Ghebremedhin, E., Rüb, U., Bratzke, H., & Del Tredici, K. (2004). Stages in the development of Parkinson’s disease-related pathology. <i>Cell and Tissue Research, 318</i>(1), 121–134. https://doi.org/10.1007/s00441-004-0956-9](https://link.springer.com/article/10.1007%2Fs00441-004-0956-9)

[2] [Jankovic, J. (2008). Parkinson’s disease: clinical features and diagnosis. <i>Journal of Neurology, Neurosurgery & Psychiatry, 79</i>(4), 368–376. https://doi.org/10.1136/jnnp.2007.131045](https://jnnp.bmj.com/content/jnnp/79/4/368.full.pdf)

[3] [Ferster, M. L., Mazilu, S., & Tröster, G. (2015). Gait Parameters Change Prior to Freezing in Parkinson's Disease: A Data-Driven Study with Wearable Inertial Units. <i>Proceedings of the 10th EAI International Conference on Body Area Networks.</i> https://doi.org/10.4108/eai.28-9-2015.2261411](https://eudl.eu/pdf/10.4108/eai.28-9-2015.2261411)

[3] [Nutt, J. G., Bloem, B. R., Giladi, N., Hallett, M., Horak, F. B., & Nieuwboer, A. (2011). Freezing of gait: moving forward on a mysterious clinical phenomenon. <i>The Lancet Neurology, 10</i>(8), 734–744. https://doi.org/10.1016/s1474-4422(11)70143-0](https://www.thelancet.com/journals/laneur/article/PIIS1474-4422(11)70143-0/fulltext)

[4] [Bloem, B. R., Hausdorff, J. M., Visser, J. E., & Giladi, N. (2004). Falls and freezing of gait in Parkinson’s disease: A review of two interconnected, episodic phenomena. <i>Movement Disorders, 19</i>(8), 871–884. https://doi.org/10.1002/mds.20115](https://onlinelibrary.wiley.com/doi/abs/10.1002/mds.20115)

[5] [Moore, O., Peretz, C., & Giladi, N. (2007). Freezing of gait affects quality of life of peoples with Parkinson’s disease beyond its relationships with mobility and gait. <i>Movement Disorders, 22</i>(15), 2192–2195. https://doi.org/10.1002/mds.21659](https://onlinelibrary.wiley.com/doi/abs/10.1002/mds.21659)

[6] [Giladi, N., & Hausdorff, J. M. (2006). The role of mental function in the pathogenesis of freezing of gait in Parkinson’s disease. <i>Journal of the Neurological Sciences, 248</i>(1–2), 173–176. https://doi.org/10.1016/j.jns.2006.05.015](https://www.jns-journal.com/article/S0022-510X(06)00207-3/fulltext)

[7] [Podsiadlo, D., & Richardson, S. (1991). The Timed “Up & Go”: A Test of Basic Functional Mobility for Frail Elderly Persons. <i>Journal of the American Geriatrics Society, 39</i>(2), 142–148. https://doi.org/10.1111/j.1532-5415.1991.tb01616.x](https://agsjournals.onlinelibrary.wiley.com/doi/abs/10.1111/j.1532-5415.1991.tb01616.x)

[8] [Hoehn, M. M., & Yahr, M. D. (1967). Parkinsonism: onset, progression, and mortality. <i>Neurology, 17</i>(5), 427. https://doi.org/10.1212/wnl.17.5.427](https://n.neurology.org/content/neurology/17/5/427.full.pdf)

[9] [Rozenfeld, S., Miskovic, A., Nitsch, K. P., & Ehrlich-Jones, L. (2017). Measurement Characteristics and Clinical Utility of the Freezing of Gait Questionnaire in Individuals With Parkinson Disease. <i>Archives of Physical Medicine and Rehabilitation, 98</i>(10), 2106–2107. https://doi.org/10.1016/j.apmr.2017.04.027](https://www.archives-pmr.org/action/showPdf?pii=S0003-9993%2817%2930389-1)

[10] [Bachlin, M., Plotnik, M., Roggen, D., Maidan, I., Hausdorff, J. M., Giladi, N., & Troster, G. (2010). Wearable Assistant for Parkinson’s Disease Patients With the Freezing of Gait Symptom. <i>IEEE Transactions on Information Technology in Biomedicine, 14</i>(2), 436–446. https://doi.org/10.1109/titb.2009.2036165](https://ieeexplore.ieee.org/document/5325884)

[11] [Alam, M. N., Garg, A., Munia, T. T. K., Fazel-Rezai, R., & Tavakolian, K. (2017). Vertical ground reaction force marker for Parkinson’s disease. <i>PLOS ONE, 12</i>(5), e0175951. https://doi.org/10.1371/journal.pone.0175951](https://storage.googleapis.com/plos-corpus-prod/10.1371/journal.pone.0175951/1/pone.0175951.pdf?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=wombat-sa%40plos-prod.iam.gserviceaccount.com%2F20201225%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20201225T065925Z&X-Goog-Expires=3600&X-Goog-SignedHeaders=host&X-Goog-Signature=2884183c8904747d6bdcfc005d254110358b51e630d3848d4dddf2d707555f7172a2b228108ee0e7363b34152042beae9fb7c5429459324d4b8027252cb320eac10d931e8e5ffa38074c28d5890ad321296dd478b446a55cafe44bc87bd15b3227b41b56fa0460c2f41b5b65c3821662304099805082e0f1c6fd4900a40d25279c1887ed79a2d7de308e5be4f68a72c9cb0d4a5f344911d4e39bea17289ebe0577588a6b8a48567fadbce57196ae407f827b7273007bff8e188830f6ac279c81da2c10000e1b4c441cc43c22bb997b5a886b0b8f0f0f88db0e81f61ff65ed5c126bc556c0d18e9e5cf7b67a3c47a158ee9f41f62e7e0273c9edde8e4fbf55322)

[12] [Pinto, C., Schuch, C. P., Balbinot, G., Salazar, A. P., Hennig, E. M., Kleiner, A. F. R., & Pagnussat, A. S. (2019). Movement smoothness during a functional mobility task in subjects with Parkinson’s disease and freezing of gait – an analysis using inertial measurement units. <i>Journal of NeuroEngineering and Rehabilitation, 16</i>(1). https://doi.org/10.1186/s12984-019-0579-8](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6729092/pdf/12984_2019_Article_579.pdf)

[13] [Kuhner, A., Schubert, T., Maurer, C., & Burgard, W. (2017). An online system for tracking the performance of Parkinson’s patients. <i>2017 IEEE/RSJ International Conference on Intelligent Robots and Systems (IROS).</i> https://doi.org/10.1109/iros.2017.8205977](https://ieeexplore.ieee.org/document/8205977)

[14] [Aich, S., Pradhan, P., Park, J., Sethi, N., Vathsa, V., & Kim, H.-C. (2018). A Validation Study of Freezing of Gait (FoG) Detection and Machine-Learning-Based FoG Prediction Using Estimated Gait Characteristics with a Wearable Accelerometer. <i>Sensors, 18</i>(10), 3287. https://doi.org/10.3390/s18103287](https://www.mdpi.com/1424-8220/18/10/3287/pdf)

[15] [Eom, G.-M., Kwon, Y., Park, S. H., Kim, J.-W., Ho, Y., Jeon, H.-M., Bang, M.-J., Jung, G.-I., Lee, S.-M., Koh, S.-B., Lee, J.-W., & Jeon, H. S. (2014). A practical method for the detection of freezing of gait in patients with Parkinson’s disease. <i>Clinical Interventions in Aging</i>, 1709. https://doi.org/10.2147/cia.s69773](https://www.dovepress.com/front_end/cr_data/cache/pdf/download_1608880224_5fe59060bcbbf/CIA-69773-a-practical-method-for-the-detection-of-freezing-of-gait-in_100814.pdf)

[16] [Moore, S. T., MacDougall, H. G., & Ondo, W. G. (2008). Ambulatory monitoring of freezing of gait in Parkinson’s disease. <i>Journal of Neuroscience Methods, 167</i>(2), 340–348. https://doi.org/10.1016/j.jneumeth.2007.08.023](https://www.sciencedirect.com/science/article/abs/pii/S0165027007004281?via%3Dihub)

[17] [Morris, T. R., Cho, C., Dilda, V., Shine, J. M., Naismith, S. L., Lewis, S. J. G., & Moore, S. T. (2012). A comparison of clinical and objective measures of freezing of gait in Parkinson’s disease. <i>Parkinsonism & Related Disorders, 18</i>(5), 572–577. https://doi.org/10.1016/j.parkreldis.2012.03.001](https://www.prd-journal.com/article/S1353-8020(12)00077-6/fulltext)

[18] [Hollman, J. H., McDade, E. M., & Petersen, R. C. (2011). Normative spatiotemporal gait parameters in older adults. <i>Gait & Posture, 34</i>(1), 111–118. https://doi.org/10.1016/j.gaitpost.2011.03.024](https://www.sciencedirect.com/science/article/abs/pii/S0966636211001019?via%3Dihub)

[19] [Alcock, L., Galna, B., Perkins, R., Lord, S., & Rochester, L. (2018). Step length determines minimum toe clearance in older adults and people with Parkinson’s disease. <i>Journal of Biomechanics, 71</i>, 30–36. https://doi.org/10.1016/j.jbiomech.2017.12.002](https://reader.elsevier.com/reader/sd/pii/S0021929017307066?token=FC40F65219DDA04CC16145A2E8FD5C99B03D72A829DD5532E48A6BA5F694F49E175967594055D8B1527195DB67C832A9)

[20] [Azevedo Coste, C., Sijobert, B., Pissard-Gibollet, R., Pasquier, M., Espiau, B., & Geny, C. (2014). Detection of Freezing of Gait in Parkinson Disease: Preliminary Results. <i>Sensors, 14</i>(4), 6819–6827. https://doi.org/10.3390/s140406819](https://www.mdpi.com/1424-8220/14/4/6819/pdf)

[21] [Morris, T. R., Cho, C., Dilda, V., Shine, J. M., Naismith, S. L., Lewis, S. J. G., & Moore, S. T. (2012). A comparison of clinical and objective measures of freezing of gait in Parkinson’s disease. <i>Parkinsonism & Related Disorders, 18</i>(5), 572–577. https://doi.org/10.1016/j.parkreldis.2012.03.001](https://www.prd-journal.com/article/S1353-8020(12)00077-6/fulltext)

[22] [Goldberger, A. L., Amaral, L. A. N., Glass, L., Hausdorff, J. M., Ivanov, P. C., Mark, R. G., Mietus, J. E., Moody, G. B., Peng, C.-K., & Stanley, H. E. (2000). PhysioBank, PhysioToolkit, and PhysioNet. <i>Circulation, 101</i>(23), 1. https://doi.org/10.1161/01.cir.101.23.e215](https://www.ahajournals.org/doi/pdf/10.1161/01.CIR.101.23.e215)

[23] [Bisong E. (2019) Google Colaboratory. In: Building Machine Learning and Deep Learning Models on Google Cloud Platform. <i>Apress, Berkeley, CA</i>. https://doi.org/10.1007/978-1-4842-4470-8_7](https://link.springer.com/chapter/10.1007%2F978-1-4842-4470-8_7)

[24] [Wen, Z., Shi, J., Li, Q., He, B., & Chen, J. (2018). ThunderSVM: A Fast SVM Library on GPUs and CPUs, <i>Journal of Machine Learning Research, 19</i>, 797–801.](https://github.com/Xtra-Computing/thundersvm/blob/master/thundersvm-full.pdf)

[25] [Morawiec, D. (n.d.). sklearn-porter - Transpile trained scikit-learn estimators to C, Java, JavaScript and others. https://github.com/nok/sklearn-porter](https://github.com/nok/sklearn-porter)
