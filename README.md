# Project structure

## Books vis batch

This project contains steps to prepare assets for android application.

These steps are as follows:
1. Parse PDF to text (I expect correct cyrylic PDF)
1. Get nouns from text
1. User should manually analyze these words if they are meaningful for application
1. Get english translation for all words
1. Group words into synonyms sets
1. Download images for english translations (just works better than for ukrainian cases)

## Book vis android

TBD

# OCR

Evaluate if we can go with Tesseract OCR API.
It's C++ library that is compilable for Linux, Mac, Windows.

I have to check if it's compilable for Android?
At a first glance there is article here:
<https://stackoverflow.com/questions/7710123/how-can-i-use-tesseract-in-android>

Some repo here:
<https://github.com/yushulx/android-tesseract-ocr>
and here (*IMHO best choise*):
<https://github.com/rmtheis/tess-two>

All these things work with Tesseract 3.x

# NLP

Read from here : <https://bnosac.github.io/udpipe/docs/doc0.html>

```
    install.packages("udpipe")
    library(udpipe)
    udmodel <- udpipe_download_model(language = "ukrainian")
    udmodel <- udpipe_load_model(file = udmodel$file_model)
    x <- udpipe_annotate(udmodel, x = "Ik ging op reis en ik nam mee: mijn laptop, mijn zonnebril en goed humeur.")
    x <- as.data.frame(x, detailed = TRUE)
    x
```
or
```
    udpipe_annotate(udmodel, x = iconv('your text', to = 'UTF-8'))
```
R I downloaded from here : <https://cran.r-project.org/bin/windows/base/>

## Java bridge for udpipe

### cz.cuni.mff.ufal.udpipe

Maven artifact is described like:

```
    <!-- https://mvnrepository.com/artifact/cz.cuni.mff.ufal.udpipe/udpipe -->
    <dependency>
        <groupId>cz.cuni.mff.ufal.udpipe</groupId>
        <artifactId>udpipe</artifactId>
        <version>1.1.0</version>
    </dependency>
```

The main site is here <http://ufal.mff.cuni.cz/udpipe>

Also I downlaoded model from here <https://lindat.mff.cuni.cz/repository/xmlui/handle/11234/1-2898>

Documentation about how to run this lib is here:
<https://github.com/ufal/udpipe/blob/master/bindings/java/examples/RunUDPipe.java>

It's necessary to download DLL from <http://ufal.mff.cuni.cz/udpipe>

Binaries I take from here: <https://github.com/ufal/udpipe/releases/tag/v1.2.0>

# LICENSE 

So far it's Apache 2.0 license
