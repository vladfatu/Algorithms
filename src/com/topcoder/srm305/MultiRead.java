package com.topcoder.srm305;

public class MultiRead {

	public final static void main(String[] args)
	{
		MultiRead read = new MultiRead();
		System.out.println(read.minCycles("RWRRWWRWRWRRRWWRRRRWRRWRRWRRRRRRRRRWRWRWRRRRWRRRRR", 4));
	}

	public int minCycles(String trace, int procs)
	{
		if (procs <= 0 || trace == null || trace.length() == 0)
		{
			return 0;
		}

		int cycles = 0;
		int tempProcs = procs;
		for (int i = 0; i < trace.length(); i++)
		{
			if (trace.charAt(i) == 'R')
			{
				if (tempProcs == procs)
				{
					cycles++;
				}
				if (tempProcs == 1)
				{
					tempProcs = procs;
				} else
				{
					tempProcs--;
				}
			} else if (trace.charAt(i) == 'W')
			{
				tempProcs = procs;
				cycles++;
			}
		}
		return cycles;
	}
}
