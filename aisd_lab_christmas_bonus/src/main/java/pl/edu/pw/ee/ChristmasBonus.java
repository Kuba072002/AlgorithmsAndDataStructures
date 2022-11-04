package pl.edu.pw.ee;

public class ChristmasBonus {
    private int[] arr;
    private int n;
    private int changes;

    public int findMaxLength(int[] schedule, int changes) {
        if (schedule.length > 99999 || changes > 99999)
            throw new IllegalArgumentException("Array and number of changes can't be bigger than 99_999");

        this.arr = schedule;
        this.n = arr.length;
        this.changes = changes;

        if (changes == 0)
            return findLongestSubarrayFor0Changes();

        int maxFrequency = 0;
        int currentFrequency = 0;

        maxFrequency = FindMaxSubarrayForIndex(0);

        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1])
                currentFrequency = FindMaxSubarrayForIndex(i);
            if (currentFrequency > maxFrequency)
                maxFrequency = currentFrequency;
        }
        return maxFrequency;
    }

    private int FindMaxSubarrayForIndex(int i) {
        int j = i + 1;
        int start = i;
        int end = 0;

        while (j < n && arr[j] == arr[start]) {
            end++;
            j++;
        }
        end = i + end;
        int c = changes;

        while (c > 0) {
            if (start == 0 && end == n - 1)
                break;
            // l get number of how many will increase length of subarray if we go to left
            // and number of changes after that move
            // similarly for r
            int[] l = toLeft(start, c);
            int[] r = toRight(end, c);
            if (l[0] > r[0]) {
                start -= l[0];
                c = l[1];
            } else if (l[0] < r[0]) {
                end += r[0];
                c = r[1];
            } else {
                if (l[1] >= r[1]) {
                    start -= l[0];
                    c = l[1];
                } else {
                    end += r[0];
                    c = r[1];
                }
            }
        }

        return end - start + 1;
    }

    private int[] toLeft(int index, int c) {
        int j = index;
        if (j - 1 < 0)
            return new int[] { 0, c };
        if (j - 1 == 0)
            return new int[] { 1, c - 1 };
        while (j - 1 >= 0 && arr[j - 1] != arr[index]) {
            if (index - j >= c)
                return new int[] { c, 0 };
            j--;
        }
        c -= index - j;

        while (j - 1 > 0 && arr[j - 1] == arr[index])
            j--;

        return new int[] { index - j, c };
    }

    private int[] toRight(int index, int c) {
        int j = index;
        if (j + 1 >= n)
            return new int[] { 0, c };
        while (j + 1 < n && arr[j + 1] != arr[index]) {
            if (j - index >= c)
                return new int[] { c, 0 };
            j++;
        }
        c -= j - index;

        while (j + 1 < n && arr[j + 1] == arr[index])
            j++;

        return new int[] { j - index, c };
    }

    private int findLongestSubarrayFor0Changes() {
        int maxFrequency = 0;
        int currentFrequency = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i - 1])
                ++currentFrequency;
            else {
                if (currentFrequency > maxFrequency)
                    maxFrequency = currentFrequency;
                currentFrequency = 1;
            }
        }

        if (currentFrequency > maxFrequency)
            maxFrequency = currentFrequency;

        return maxFrequency;
    }
}
