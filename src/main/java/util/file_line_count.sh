#!/usr/bin/env bash

echo `find . -name *.java | xargs cat | wc -l`