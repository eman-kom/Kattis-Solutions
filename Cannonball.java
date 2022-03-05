import java.lang.Math;
import java.util.*;
import java.io.*;

public class Cannonball {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		double[] start = {sc.nextFloat(), sc.nextFloat(), 0};
		double[] end   = {sc.nextFloat(), sc.nextFloat(), 0};
		int n = sc.nextInt();

		ArrayList<double[]> vertices = new ArrayList<>();
		vertices.add(start);
		vertices.add(end);

		for (int i = 0; i < n; i++) {
			double[] cannon = {sc.nextFloat(), sc.nextFloat(), 1};
			vertices.add(cannon);
		}

		ArrayList<double[]> edgeList = new ArrayList<>();

		for (int u = 0; u < vertices.size(); u++) {
			for (int v = 0; v < vertices.size(); v++) {
				double distance = Math.hypot(vertices.get(u)[0] - vertices.get(v)[0], vertices.get(u)[1] - vertices.get(v)[1]);
				double time = vertices.get(u)[2] == 1 ? 2 + Math.abs(distance - 50) / 5 : distance / 5;
				double[] edge = {u, v, time};
				edgeList.add(edge);
			}
		}

		double[] total = new double[vertices.size()];
		Arrays.fill(total, Double.MAX_VALUE);
		total[0] = 0;

		for (int i = 0; i < total.length; i++) {
			for (int j = 0; j < edgeList.size(); j++) {
				if (total[(int)edgeList.get(j)[1]] > total[(int)edgeList.get(j)[0]] + edgeList.get(j)[2]) {
					total[(int)edgeList.get(j)[1]] = total[(int)edgeList.get(j)[0]] + edgeList.get(j)[2];
				}
			}
		}

		System.out.println(total[1]);
	}
}