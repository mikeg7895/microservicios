using Microsoft.EntityFrameworkCore;

namespace Rest.Models;

public partial class DetectionContext : DbContext
{
    public DetectionContext(DbContextOptions<DetectionContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Video> Videos { get; set; }

}
