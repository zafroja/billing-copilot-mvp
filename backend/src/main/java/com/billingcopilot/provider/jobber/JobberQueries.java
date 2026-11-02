package com.billingcopilot.provider.jobber;
public final class JobberQueries {
 public static final String ACCOUNT="query GetAccount { account { id name } }";
 public static final String JOBS="query GetJobs($first:Int!){ jobs(first:$first){ nodes { id jobNumber title } pageInfo { hasNextPage endCursor } } }";
 private JobberQueries(){}
}
