package com.nomachetejuggling.geneticrypt.benchmark

class BenchmarkText {
    private List<String> words
    private Random random

    public BenchmarkText() {
        this(new Random())
    }

    public BenchmarkText(Random random) {
        this.random = random
        this.words = []
        def matcher = SAMPLE_TEXT.replaceAll(/[^A-Za-z \t\r\n]/,"") =~ /\b([a-zA-Z']+?)\b/
        matcher.each {
            words << it[1]
        }
    }

    public String getText(int length) {
        def offset = random.nextInt(words.size())
        List<String> textwords = []
        for (int i=0;i<length;i++) {
            textwords << words[(i+offset) % words.size()]
        }
        return textwords.join(" ");
    }

    private static final String SAMPLE_TEXT = "\"Well, of all the hare-brained proposals I ever listened to, this takes\n" +
            "the bun\"; and Felix Muller adjusted his pince-nez and lay back in his\n" +
            "chair and laughed softly.\n" +
            "\n" +
            "\"But why hare-brained?\" asked his companion, seriously. \"Singular, I\n" +
            "admit it may be; startling if you like, but I do not see that there is\n" +
            "anything in it to laugh at.\"\n" +
            "\n" +
            "\"You don't?\" and the lawyer's face became suddenly grave. \"Do you\n" +
            "realise what your proposal implies?\"\n" +
            "\n" +
            "\"I think I do,\" and Rufus Sterne's face flushed slightly; \"but you are\n" +
            "thinking of a contingency that will never arise.\"\n" +
            "\n" +
            "\"Perhaps I am; but every contingency must be guarded against,\" and Felix\n" +
            "Muller took off his glasses and wiped them meditatively. \"You say you\n" +
            "are confident of success, and I am bound to admit, from what I know of\n" +
            "you and your scheme, I think your confidence is well founded. But you\n" +
            "know as well as I do, that nothing is certain in this world but death.\"\n" +
            "\n" +
            "\"Well?\"\n" +
            "\n" +
            "\"You may fail. Something may happen you cannot foresee.\"\n" +
            "\n" +
            "\"I grant it, as a remote--an exceedingly remote--possibility. But in\n" +
            "such an event you will be covered by my life assurance policy.\"\n" +
            "\n" +
            "\"But you may live for another fifty years.\"\n" +
            "\n" +
            "Rufus Sterne shook his head and smiled gravely.\n" +
            "\n" +
            "\"If I fail,\" he said, \"I shall have no further use for life. You need be\n" +
            "under no apprehension on that score. The money for which my life is\n" +
            "insured will be paid into your hands without any unnecessary delay. I\n" +
            "know the company.\"\n" +
            "\n" +
            "\"But it would be a direct contravention of the law, and would entitle\n" +
            "the company to refuse----\"\n" +
            "\n" +
            "\"My dear sir,\" Sterne interrupted, sharply, \"there are many roads into\n" +
            "the land of oblivion. Exits can be arranged, if the parties so desire,\n" +
            "in a perfectly natural manner. You need not fear that trouble will arise\n" +
            "on that score.\"\n" +
            "\n" +
            "\"Nevertheless, I confess I do not like the proposal.\"\n" +
            "\n" +
            "\"You seem to have grown suddenly very squeamish,\" Sterne said, with a\n" +
            "slight curl of the lip. \"I have always understood that you set no\n" +
            "particular value on human life. Indeed, I have heard you argue that a\n" +
            "man's life is his own to do as he likes with--to continue it or end it,\n" +
            "as seems good in his own eyes.\"\n" +
            "\n" +
            "\"I am still of the same opinion. No, I am no sentimentalist. The rubbish\n" +
            "talked by parsons and so-called humanitarians makes me ill. All the same\n" +
            "I would prefer that someone else----\"\n" +
            "\n" +
            "\"There is no one else,\" Rufus Sterne broke in, irritably. \"You are my\n" +
            "last hope. A thousand pounds now will lead me on to fame and fortune.\n" +
            "You have the money. You can lend it to me if you like, and for security\n" +
            "I make you my sole legatee.\"\n" +
            "\n" +
            "\"But the money is not mine, and must be paid back by the 31st of\n" +
            "December of next year without fail.\"\n" +
            "\n" +
            "\"That gives eighteen months and more,\" and Sterne laughed. \"My dear\n" +
            "fellow, six months or a little more will see the thing through.\"\n" +
            "\n" +
            "\"I like to see a man confident,\" Felix Muller said, a little uneasily.\n" +
            "\"But there is such a thing as over-confidence, as you know. I should be\n" +
            "better pleased if you were a little less cocksure.\"\n" +
            "\n" +
            "\"But man alive, I have been working at this thing for years. I have\n" +
            "tested every link in the chain, if you will allow me to say so. I have\n" +
            "faced every possible contingency. I have gone over the ground so often\n" +
            "that I know every inch of the way. I have anticipated every objection,\n" +
            "every weakness, every flaw, and have provided against it. All I want now\n" +
            "is a thousand pounds in hard cash, and in a year's time I shall be able\n" +
            "to repay it ten-fold.\"\n" +
            "\n" +
            "\"You hope so.\"\n" +
            "\n" +
            "\"I am sure of it; as far as a man can be sure of anything in this stupid\n" +
            "world. The more or less unpleasant contingency that you persist in\n" +
            "looking at will never occur.\"\n" +
            "\n" +
            "\"But it may occur,\" Muller persisted.\n" +
            "\n" +
            "\"Well, if it does you will not suffer; and I shall be glad to hide\n" +
            "myself and be at rest.\"\n" +
            "\n" +
            "\"You say that now.\"\n" +
            "\n" +
            "\"Do you doubt my courage or my honour?\" Sterne demanded, sharply.\n" +
            "\n" +
            "\"No, I doubt neither,\" Muller said, slowly; \"but the instinct of life is\n" +
            "strong--especially in the young.\"\n" +
            "\n" +
            "\"When a man has something to live for--some great purpose to achieve, or\n" +
            "some proud ambition to realise, he naturally wants to live. But take\n" +
            "away that something, and life is a squeezed orange which he is glad to\n" +
            "fling away.\"\n" +
            "\n" +
            "\"People still cling to life when they have nothing left to live for,\"\n" +
            "Muller said, reflectively.\n" +
            "\n" +
            "\"Sentimentalists and cowards,\" Sterne broke in, hastily. \"Men who have\n" +
            "been robbed of their courage by priestly superstitions. But you and I\n" +
            "have thrown off the swaddling clothes in which we were reared. Your\n" +
            "German philosophers have not reflected and written for nothing.\"\n" +
            "\n" +
            "\"I am an Englishman,\" Muller broke in, hastily.\n" +
            "\n" +
            "\"I do not dispute it for a moment,\" Sterne said, with a laugh. \"But let\n" +
            "us not get away from the subject we have in hand. The question is will\n" +
            "you accommodate me or will you not?\"\n" +
            "\n" +
            "\"If I do not you will curse me to-day,\" Muller said, with a drawl; \"and\n" +
            "if I do, you may curse me more bitterly eighteen months hence. So it\n" +
            "seems to me it is a choice between two evils.\"\n" +
            "\n" +
            "\"There you are mistaken,\" Sterne replied. \"I certainly shall curse you\n" +
            "if you refuse me, but if you become my friend to-day I shall never cease\n" +
            "to bless you.\"\n" +
            "\n" +
            "\"Not if you fail?\"\n" +
            "\n" +
            "\"Why will you persist in harping on that one string? I shall not fail.\n" +
            "Failure is out of the reckoning. I am as certain of success as I am of\n" +
            "my own existence.\"\n" +
            "\n" +
            "\"'Let him that thinketh he standeth take heed lest he fall.'\"\n" +
            "\n" +
            "\"Please, Muller, don't quote the Bible to me.\"\n" +
            "\n" +
            "\"It is sound philosophy wherever it is taken from. Besides, the Bible is\n" +
            "good literature.\"\n" +
            "\n" +
            "\"So is Dante's 'Inferno.' But if you were dosed with it morning, noon\n" +
            "and night, for the space of fifteen or twenty years, you would be glad\n" +
            "to have a little respite. But we are getting away again from the subject\n" +
            "in hand. Let's stick to the one point till we've done with it. If you've\n" +
            "made up your mind that you won't help me, say so.\""
}
