# Project motivation

Autism never come alone.
Alot of kids with autism has dislexia.
Even if kids has no dislexia, they might have troubles in understanding of texts.
It could be hard for them to understand math in elementary school if they just can't read challenge.

[![](http://img.youtube.com/vi/hsPoSONTx3I/0.jpg)](http://www.youtube.com/watch?v=hsPoSONTx3I "")

In Ukraine we have books for elementary school at 
https://mon.gov.ua/ua/osvita/zagalna-serednya-osvita/pidruchniki/elektronni-pidruchniki/elektronni-pidruchniki-dlya-1-klasu/pidruchniki-dlya-1-klasu-shkil-z-ukrayinskoyu-movoyu-navchannya/matematika-1-klas

The whole idea is to create app that helps to translate challenge from text into set of pictures.
Kid or teacher or teacher assistant helps to choose challenge on tablet, and application provides scene and set of pictures related to what is written in book.
This application would be helpful for quick visualization of "what is going on in this book".

picture to explain, how text will be converted to set of pictures

application wireframe placeholder.

The plan is to develop set of tools to be able quickly prepare such set of application.
First of all I plan to develop it for Ukrainian books.

## How to build and contribute

TBD

## Books vis batch

This project contains steps to prepare assets for android application.

These steps are as follows:
1. Parse PDF to text (I expect correct cyrylic PDF)
1. Get nouns from text
1. User should manually analyze these words if they are meaningful for application
1. Get english translation for all words
1. Group words into synonyms sets
1. Download images for english translations (just works better than for ukrainian cases)

[Technical decisions](documentation/decisions_batch.md)

## Book vis android

TBD


# LICENSE 

So far it's Apache 2.0 license
