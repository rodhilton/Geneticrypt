package com.nomachetejuggling.geneticrypt.benchmark

import com.nomachetejuggling.geneticrypt.util.SampleText

class BenchmarkText {
    private List<String> words
    private Random random

    public BenchmarkText() {
        this(new Random())
    }

    public BenchmarkText(Random random) {
        this.random = random
        this.words = []
    }

    public String getText(int length) {
        def offset = random.nextInt(SampleText.text.length() - length)
        SampleText.getText().substring(offset,offset+length)
    }
}
