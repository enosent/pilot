package eliza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Eliza main class. Stores the processed script. Does the input
 * transformations.
 */
public class Eliza {

	// final boolean echoInput = false;
	// final boolean printData = false;
	//
	// final boolean printKeys = false;
	// final boolean printSyns = false;
	// final boolean printPrePost = false;
	// final boolean printInitialFinal = false;

	final boolean echoInput = true;
	final boolean printData = true;

	final boolean printKeys = true;
	final boolean printSyns = true;
	final boolean printPrePost = true;
	final boolean printInitialFinal = true;

	/** The key list */
	KeyList keys = new KeyList();
	/** The syn list */
	SynList syns = new SynList();
	/** The pre list */
	PrePostList pre = new PrePostList();
	/** The post list */
	PrePostList post = new PrePostList();
	/** Initial string */
	String initial = "Hello.";
	/** Final string */
	String finl = "Goodbye.";
	/** Quit list */
	WordSet quit = new WordSet();

	/** Key stack */
	KeyStack keyStack = new KeyStack();

	/** Memory */
	Mem mem = new Mem();

	DecompList lastDecomp;
	ReasembList lastReasemb;
	boolean finished = false;

	static final int success = 0;
	static final int failure = 1;
	static final int gotoRule = 2;

	public boolean finished() {
		return finished;
	}

	/**
	 * Process a line of script input.
	 */
	public void collect(String s) {
		String lines[] = new String[4];

		if (EString.match(s, "*reasmb: *", lines)) {
			if (lastReasemb == null) {
				System.out.println("Error: no last reasemb");
				return;
			}
			lastReasemb.add(lines[1]);
		} else if (EString.match(s, "*decomp: *", lines)) {
			if (lastDecomp == null) {
				System.out.println("Error: no last decomp");
				return;
			}
			lastReasemb = new ReasembList();
			String temp = new String(lines[1]);
			if (EString.match(temp, "$ *", lines)) {
				lastDecomp.add(new Decomp(lines[0], true, lastReasemb));
			} else {
				lastDecomp.add(new Decomp(temp, false, lastReasemb));
			}
		} else if (EString.match(s, "*key: * #*", lines)) {
			lastDecomp = new DecompList();
			lastReasemb = null;
			int n = 0;
			if (lines[2].length() != 0) {
				try {
					n = Integer.parseInt(lines[2]);
				} catch (NumberFormatException e) {
					System.out.println("Number is wrong in key: " + lines[2]);
				}
			}
			keys.add(new Key(lines[1], n, lastDecomp));
		} else if (EString.match(s, "*key: *", lines)) {
			lastDecomp = new DecompList();
			lastReasemb = null;
			keys.add(new Key(lines[1], 0, lastDecomp));
		} else if (EString.match(s, "*synon: * *", lines)) {
			WordSet words = new WordSet();
			words.add(lines[1]);
			s = lines[2];
			while (EString.match(s, "* *", lines)) {
				words.add(lines[0]);
				s = lines[1];
			}
			words.add(s);
			syns.add(words);
		} else if (EString.match(s, "*pre: * *", lines)) {
			pre.add(new PrePost(lines[1], lines[2]));
		} else if (EString.match(s, "*post: * *", lines)) {
			post.add(new PrePost(lines[1], lines[2]));
		} else if (EString.match(s, "*initial: *", lines)) {
			initial = lines[1];
		} else if (EString.match(s, "*final: *", lines)) {
			finl = lines[1];
		} else if (EString.match(s, "*quit: *", lines)) {
			quit.add(" " + lines[1] + " ");
		} else {
			System.out.println("Unrecognized input: " + s);
		}
	}

	/**
	 * Print the stored script.
	 */
	public void print() {
		if (printKeys)
			keys.print(0);
		if (printSyns)
			syns.print(0);
		if (printPrePost) {
			pre.print(0);
			post.print(0);
		}
		if (printInitialFinal) {
			System.out.println("initial: " + initial);
			System.out.println("final:   " + finl);
			quit.print(0);
		}
	}

	/**
	 * Process a line of input.
	 */
	public String processInput(String s) {
		String reply;
		// Do some input transformations first.
		s = EString.translate(s, "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
				"abcdefghijklmnopqrstuvwxyz");
		s = EString.translate(s, "@#$%^&*()_-+=~`{[}]|:;<>\\\"",
				"                          ");
		s = EString.translate(s, ",?!", "...");
		// Compress out multiple speace.
		s = EString.compress(s);
		String lines[] = new String[2];
		// Break apart sentences, and do each separately.
		while (EString.match(s, "*.*", lines)) {
			reply = sentence(lines[0]);
			if (reply != null)
				return reply;
			s = EString.trim(lines[1]);
		}
		if (s.length() != 0) {
			reply = sentence(s);
			if (reply != null)
				return reply;
		}
		// Nothing matched, so try memory.
		String m = mem.get();
		if (m != null)
			return m;

		// No memory, reply with xnone.
		Key key = keys.getKey("xnone");
		if (key != null) {
			Key dummy = null;
			reply = decompose(key, s, dummy);
			if (reply != null)
				return reply;
		}
		// No xnone, just say anything.
		return "I am at a loss for words.";
	}

	/**
	 * Process a sentence. (1) Make pre transformations. (2) Check for quit
	 * word. (3) Scan sentence for keys, build key stack. (4) Try decompositions
	 * for each key.
	 */
	String sentence(String s) {
		s = pre.translate(s);
		s = EString.pad(s);
		if (quit.contains(s)) {
			finished = true;
			return finl;
		}
		keys.buildKeyStack(keyStack, s);
		for (int i = 0; i < keyStack.keyTop(); i++) {
			Key gotoKey = new Key();
			String reply = decompose(keyStack.key(i), s, gotoKey);
			if (reply != null)
				return reply;
			// If decomposition returned gotoKey, try it
			while (gotoKey.key() != null) {
				reply = decompose(gotoKey, s, gotoKey);
				if (reply != null)
					return reply;
			}
		}
		return null;
	}

	/**
	 * Decompose a string according to the given key. Try each decomposition
	 * rule in order. If it matches, assemble a reply and return it. If assembly
	 * fails, try another decomposition rule. If assembly is a goto rule, return
	 * null and give the key. If assembly succeeds, return the reply;
	 */
	String decompose(Key key, String s, Key gotoKey) {
		String reply[] = new String[10];
		for (int i = 0; i < key.decomp().size(); i++) {
			Decomp d = (Decomp) key.decomp().get(i);
			String pat = d.pattern();
			if (syns.matchDecomp(s, pat, reply)) {
				String rep = assemble(d, reply, gotoKey);
				if (rep != null)
					return rep;
				if (gotoKey.key() != null)
					return null;
			}
		}
		return null;
	}

	/**
	 * Assembly a reply from a decomp rule and the input. If the reassembly rule
	 * is goto, return null and give the gotoKey to use. Otherwise return the
	 * response.
	 */
	String assemble(Decomp d, String reply[], Key gotoKey) {
		String lines[] = new String[3];
		d.stepRule();
		String rule = d.nextRule();
		if (EString.match(rule, "goto *", lines)) {
			// goto rule -- set gotoKey and return false.
			gotoKey.copy(keys.getKey(lines[0]));
			if (gotoKey.key() != null)
				return null;
			System.out.println("Goto rule did not match key: " + lines[0]);
			return null;
		}
		String work = "";
		while (EString.match(rule, "* (#)*", lines)) {
			// reassembly rule with number substitution
			rule = lines[2]; // there might be more
			int n = 0;
			try {
				n = Integer.parseInt(lines[1]) - 1;
			} catch (NumberFormatException e) {
				System.out.println("Number is wrong in reassembly rule "
						+ lines[1]);
			}
			if (n < 0 || n >= reply.length) {
				System.out.println("Substitution number is bad " + lines[1]);
				return null;
			}
			reply[n] = post.translate(reply[n]);
			work += lines[0] + " " + reply[n];
		}
		work += rule;
		if (d.mem()) {
			mem.save(work);
			return null;
		}
		return work;
	}

	public void readScript(Reader reader) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		String line;
		while ((line = br.readLine()) != null) {
			collect(line);
		}
		// print();
	}

}
