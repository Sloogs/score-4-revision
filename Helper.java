public class Helper
{
	public static int translateABCDToNumber(String coord)
	{
		String[] letters = {"A", "B", "C", "D"};
		String letterToTranslate = coord.substring(0);
		int xCoord = 0;
		Debug.log("Letter given: " + coord + ", "
				+ "letter to translate: " + letterToTranslate);
		for (int i = 0; i < letters.length; i++)
		{
			boolean match = (letterToTranslate.matches(letters[i].toLowerCase()));
			Debug.log("Letter in loop: " + letters[i] + ", "
					+ "match?: " + match);
			if (letterToTranslate.matches(letters[i].toLowerCase())) {
				xCoord = i;
			}
		}
		Debug.log("Translated to coordinate: " + xCoord);

		return xCoord;
	}
}