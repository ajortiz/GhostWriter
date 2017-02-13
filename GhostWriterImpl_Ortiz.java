package writing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GhostWriterImpl_Ortiz implements GhostWriter
{	
	String text;
	
	public GhostWriterImpl_Ortiz(String inputText)
	{
		this.text = inputText;
		
	}
	

	//part of pre: seed != null : "seed is null!";
	//part of pre: seed.length() <= 10 : 
	//	"seed.length() = " + seed.length() + " > 10!";
	//part of pre: selectionList != null : 
	//	"selectionList is null!";
	//part of pre: getInputText().indexOf(seed) != -1 : 
	//	"getInputText().indexOf(" + seed + ") is -1!";
	//part of post: rv.startsWith(seed)
	//part of post: rv.length() == selectionList.size()
	public String getInputText()
	{
		return text;
	}
	
	public String generate(String seed, List<Integer> selectionList)
	{
		assert seed != null : "seed is null!";
		assert seed.length() <= 10 : "seed.length() = " + seed.length() + " > 10!";
		assert selectionList != null : "selectionList is null!";
		assert getInputText().indexOf(seed) != -1 : "getInputText().indexOf(" + seed + ") is -1!";
		
		
		String tempText = seed;
		
		
		
		Map<String, List<Character>>stringToSuffixListMap = new HashMap<String, List<Character>>();
		int intOfCharToAddToOutputText;
		for(int i = seed.length() -1 ; i < this.text.length() - 1; i++)
		{
			int startingIndex = i - (seed.length()-1);
			String startingString = this.text.substring(startingIndex, i+1);
			Character characterToAdd = this.text.charAt(i + 1);
			
			
			if(stringToSuffixListMap.get(startingString) != null)
			{
				List<Character> sortedSuffixList =  stringToSuffixListMap.get(startingString);
				sortedSuffixList.add(characterToAdd);
				Collections.sort(sortedSuffixList);
				stringToSuffixListMap.put(startingString,sortedSuffixList);
			}
			else
			{
				List<Character> sortedSuffixList = new ArrayList<Character>();
				sortedSuffixList.add(characterToAdd);
				stringToSuffixListMap.put(startingString, sortedSuffixList);
			}
		}
		
		for(int i = seed.length() - 1; i < selectionList.size() -1 ;i++)
		{
			String chosenWord = tempText.substring(tempText.length() - seed.length(), tempText.length());
			 
			intOfCharToAddToOutputText = (selectionList.get(i+1)) % (stringToSuffixListMap.get(chosenWord).size());
			List <Character> resultString = stringToSuffixListMap.get(chosenWord);
			char selectedCharFromSuffixList = resultString.get(intOfCharToAddToOutputText);
			tempText = tempText + selectedCharFromSuffixList;
	
		
		}
			return tempText;

		
	 }
	
	
	@Override
	public String getFirstNameOfSubmitter() 
	{
		return "Amanda";
	}

	@Override
	public String getLastNameOfSubmitter() {
		return " Ortiz";
	}
	
	@Override
	public double getHoursSpentWorkingOnThisAssignment() 
	{
		return 6;
	}

	@Override
	public int getScoreAgainstTestCasesSubset() 
	{
		return 60;
	}
}